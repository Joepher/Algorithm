package practice;

import java.util.List;

public class TermFrenCounter {
    public static void main(String[] args) {
        String str = "��ã�����˭����������";
        Term term = new Term();
        List<Term> highFrenqTerms = term.findHighFrenqTerm(term.findFrenqTerm(str));

        if (highFrenqTerms.size() == 0)
            System.err.println("ERROR OCCURS");
        else {
            System.out.println("maxFrenq = " + highFrenqTerms.get(0).getFrenq());
            for (Term highFrenqTerm : highFrenqTerms)
                System.out.print(" " + highFrenqTerm.getWordTerm());
        }
    }
}
