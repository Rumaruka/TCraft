package com.rumaruka.tcraft.common.guide;

import java.util.List;
import java.util.Stack;

public class BookNavigator {

    private Stack<String> visitPage=new Stack();
    List<Page.Word> words;


    public String getCurrentPage()
    {
        return this.visitPage.empty() ? null : (String)this.visitPage.peek();
    }

}
