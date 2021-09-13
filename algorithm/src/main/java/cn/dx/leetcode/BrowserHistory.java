package cn.dx.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/contest/weekly-contest-192/problems/design-browser-history/
 */
public class BrowserHistory {

    private Stack<String> mainStack,backStack;

    public BrowserHistory(String homepage) {
        mainStack = new Stack<>();
        backStack = new Stack<>();
        mainStack.push(homepage);
    }

    public void visit(String url) {
        backStack.clear();
        mainStack.push(url);
    }

    public String back(int steps) {
        if (steps >= mainStack.size() - 1){
            steps = mainStack.size() - 1;
        }
        for (int i = 0; i < steps; i++) {
            backStack.push(mainStack.pop());
        }
        return mainStack.peek();
    }

    public String forward(int steps) {
        if (steps >= backStack.size()){
            steps = backStack.size();
        }
        for (int i = 0; i < steps; i++) {
            mainStack.push(backStack.pop());
        }
        return mainStack.peek();
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        browserHistory.back(1);                   // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        browserHistory.back(1);                   // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        browserHistory.forward(1);                // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        browserHistory.forward(2);                // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        browserHistory.back(2);                   // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
        browserHistory.back(7);
    }
}
