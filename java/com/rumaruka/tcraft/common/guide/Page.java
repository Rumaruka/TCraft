package com.rumaruka.tcraft.common.guide;

import com.rumaruka.tcraft.api.guide.IBookPage;
import com.rumaruka.tcraft.api.guide.IMultiPlan;
import com.rumaruka.tcraft.api.guide.PageEnum;
import com.rumaruka.tcraft.core.misc.CoreMisc;
import com.rumaruka.tcraft.utils.PrefixUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.core.Core;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.*;

public class Page implements IBookPage, Iterable<Page> {


    private final String id;
    private String title;
    private List<String> paragraphs = new ArrayList<>();
    private Image image;
    private ItemStack imageStack;
    private List<Page> children = new ArrayList();
    private Page next;
    private Page prev;
    private Page parent;

    private static final int LINE_HEIGHT = 9;
    protected static final int PAGE_WIDTH = 136;
    private static final int SPACE_LIN = 2;
    private static final String FORMAT_CHAR = "§";
    private static final String FORMAT_CLEAR = "§r";

    private static final int HotTextColor = -855703552;
    private static final int TextColor = -872402176;
    private static final int TitleColor = -872410880;
    private static final int PaginateColor = -1728044544;


    public Page(String id, Book book) {
        this.id = PrefixUtil.withPrefix(id);
        book.addToBook(this);
    }


    @Override
    public IBookPage title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public IBookPage para(String res) {
        return para(res, false);
    }

    public IBookPage createPage(String id, PageEnum pageType) {
        return addPage(new Page(id, CoreMisc.book()), pageType);
    }

    @Override
    public IBookPage para(String resource, boolean createSiblings) {

        int pageNumber;
        Page currentPage;
        String rootId;
        String rootTitle;
        if (createSiblings) {
            List<String> pages = paginate(I18n.format(resource), 136, 120);
            pageNumber = 1;
            currentPage = this;
            rootId = this.id;
            rootTitle = this.title;
            for (String page : pages) {
                if (pageNumber == 1) {
                    this.paragraphs.add(page);
                } else {
                    currentPage = currentPage.addPage(new Page(rootId + pageNumber, CoreMisc.book()), PageEnum.SIBLING);
                    currentPage.title(rootTitle);
                    currentPage.paragraphs.add(page);
                }
                pageNumber++;
            }
        } else {
            this.paragraphs.add(I18n.format(resource));
        }
        return this;
    }

    @Override
    public IBookPage image(String paramString, int paramInt1, int paramInt2) {
        return null;
    }

    @Override
    public IBookPage image(ItemStack paramItemStack) {
        return null;
    }

    @Override
    public IBookPage createPage(String text, PageEnum typePage, ItemStack image) {
        return null;
    }

    @Override
    public IBookPage createPage(String text, PageEnum typePage, IMultiPlan multiPlan) {
        return null;
    }

    @Override
    public Iterator<Page> iterator() {
        return this.children.iterator();
    }

    public String getID() {
        return this.id;
    }

    public static class Image {
        final int width;
        final int height;
        final ResourceLocation location;

        public Image(ResourceLocation location, int w, int h) {
            this.location = location;
            this.width = w;
            this.height = h;
        }

    }

    private List<String> paginate(String text, int maxWidth, int maxHeight) {
        List<String> pages = new ArrayList();

        int x = 0;
        int headerHeight = getHeaderHeight(true);
        int y = headerHeight;
        int tagDepth = 0;
        StringBuilder tag = null;
        StringBuilder word = new StringBuilder();

        int tagStartIndex = -1;
        int startIndex = 0;

        int index = 0;
        for (int length = text.length(); index < length; index++) {
            char c = text.charAt(index);
            switch (c) {
                case '[':
                    tag = new StringBuilder();
                    tag.append(c);
                    if (tagDepth == 0) {
                        tagStartIndex = index;
                    }
                    break;
                case ']':
                    if (tag != null) {
                        tag.append(c);
                        String tagText = tag.toString();
                        if (tagText.equals("[br]")) {
                            if (y + 9 + 1 > maxHeight) {
                                int endIndex = tagStartIndex >= 0 ? tagStartIndex : index - word.length();
                                pages.add(text.substring(startIndex, endIndex));
                                startIndex = endIndex;
                                x = 0;
                                y = getHeaderHeight(false);
                                word = new StringBuilder();
                            } else if ((y > 0) || (x > 0)) {
                                x = 0;
                                y += 10;
                                word = new StringBuilder();
                            }
                        } else if (tagText.equals("[/]")) {
                            tagDepth--;
                        } else {
                            tagDepth++;
                        }
                        tag = null;
                        if (tagDepth == 0) {
                            if (y + 9 + 1 > maxHeight) {
                                pages.add(text.substring(startIndex, tagStartIndex));
                                startIndex = tagStartIndex;
                                x = 0;
                                y = getHeaderHeight(false);
                            }
                            tagStartIndex = -1;
                        }
                    }
                    break;
                case '\t':
                case ' ':
                    if (tag != null) {
                        tag.append(c);
                    } else {
                        int wordLen = lengthOf(word);
                        if (x + wordLen > maxWidth) {
                            if (y + 9 > maxHeight) {
                                int endIndex = tagStartIndex >= 0 ? tagStartIndex : index - word.length();
                                pages.add(text.substring(startIndex, endIndex));
                                startIndex = endIndex;
                                x = 0;
                                y = getHeaderHeight(false);
                            } else {
                                x = wordLen + 2;
                                y += 10;
                            }
                        } else {
                            x += wordLen + 2;
                        }
                        word = new StringBuilder();
                    }
                    break;
                default:
                    if (tag != null) {
                        tag.append(c);
                    } else {
                        word.append(c);
                    }
                    break;
            }
        }
        if (startIndex >= 0) {
            pages.add(text.substring(startIndex, tagStartIndex >= 0 ? tagStartIndex : index));
        }
        return pages;
    }

    private int getHeaderHeight(boolean firstPage) {
        return (this.title != null ? MathHelper.ceil(15.0D) : 0) + (firstPage ? (this.image != null ? this.image.height + 4 : 0) + (this.imageStack != ItemStack.EMPTY ? 18 : 0) : 0);
    }

    private int lengthOf(StringBuilder text) {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(text.toString());
    }

    public Page addPage(Page page, PageEnum pageType) {
        switch (pageType) {
            case CHILD:
                this.children.add(page);

                Page currentPage = this;
                while (currentPage.prev != null) {
                    currentPage = currentPage.prev;
                }
                page.setParent(currentPage);
            case SIBLING:
                Page current = this;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = page;
                page.prev = current;
                page.setParent(current.parent);
        }
        return page;

    }

    public boolean hasChildren() {
        return !this.children.isEmpty();
    }


    public void setParent(Page chapter) {
        this.parent = chapter;
    }

    public Page getParent() {
        return this.parent;
    }


    private static abstract interface IToken {
        public abstract void append(char paramChar);

        public abstract void finish(Stack<Format> paramStack, List<Page.Word> paramList);

        public abstract boolean allowSpace(Stack<Page.Format> paramStack);
    }

    private static class Format implements Page.IToken {
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        boolean buildValue;
        private static final Map<String, String> styles = new HashMap<>() ;

        public void append(char c) {
            if (c == ' ') {
                this.buildValue = true;
            } else if (this.buildValue) {
                this.value.append(c);
            } else {
                this.key.append(c);
            }
        }

        @Override
        public void finish(Stack<Format> paramStack, List<Word> paramList) {

        }

        @Override
        public boolean allowSpace(Stack<Format> paramStack) {
            return false;
        }
    }

    public class Word {
    }
}
