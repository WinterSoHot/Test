/***
 * Excerpted from "Functional Programming Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/mbfpp for more book information.
 ***/
package cn.dx.tinyweb;

import java.util.List;
import java.util.Map;

public interface RenderingStrategy {

    /**
     * 渲染界面
     *
     * @param model 控制处理后结果
     * @return 结果
     */
    String renderView(Map<String, List<String>> model);

}
