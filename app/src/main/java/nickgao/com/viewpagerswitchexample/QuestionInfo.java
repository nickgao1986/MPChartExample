package nickgao.com.viewpagerswitchexample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionInfo implements Serializable {


    /**
     * 题目
     */
    public String title = "";


    /**
     * 题目答案（题目选项数组KEY+1）
     */
    public String answer = "";

    /**
     * 解释
     */
    public String explain = "";


    /**
     * 今日是否答题：1-已答题、2-未答题,-1是网络异常type
     */
    public String type = "1";

    /**
     * 今日是否答题：1-已答题、2-未答题,-1是网络异常type,3-完成所有题目
     */
    public int card_type;

    /**
     * 答题结果：0-错误、1-正确
     */
    public String is_correct = "";



    /**
     * 题目选项,["选项A","选项B"]
     */
    public List<String> options = new ArrayList<>();

    /**
     * 用户选择的选项,从1开始，选项A就是1
     */
    public String option = "";

    public int have_answer = -1;


}
