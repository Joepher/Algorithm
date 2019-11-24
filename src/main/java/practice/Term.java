package practice;

import java.util.ArrayList;
import java.util.List;

public class Term
{
	private char wordTerm;
	private int frenq;

	public Term()
	{
	}

	public Term(char wordTerm, int frenq)
	{
		this.wordTerm = wordTerm;
		this.frenq = frenq;
	}

	public char getWordTerm()
	{
		return wordTerm;
	}

	public void setWordTerm(char wordTerm)
	{
		this.wordTerm = wordTerm;
	}

	public int getFrenq()
	{
		return frenq;
	}

	public void setFrenq(int frenq)
	{
		this.frenq = frenq;
	}

	public List<Term> findFrenqTerm(String str)
	{
		List<Term> frengTerms = new ArrayList<Term>();

		int frenqTermsSize, index = 1;
		frengTerms.add(new Term(str.charAt(0), 1));

		while (index < str.length())
		{
			boolean insertFlag = false;
			frenqTermsSize = frengTerms.size();
			for (int i = 0; i < frenqTermsSize; i++)
			{
				if (str.charAt(index) == frengTerms.get(i).getWordTerm())
				{
					frengTerms.get(i).setFrenq(frengTerms.get(i).getFrenq() + 1);
					insertFlag = true;
				}
			}
			if (insertFlag == false)
			{
				frengTerms.add(new Term(str.charAt(index), 1));
			}
			++index;
		}

		return frengTerms;
	}

	public List<Term> findHighFrenqTerm(List<Term> frenqTerms)
	{
		List<Term> highFrenqTerms = new ArrayList<Term>();

		int maxFrenq = 1;
		for (int i = 0; i < frenqTerms.size(); i++)
		{
			if (frenqTerms.get(i).getFrenq() > maxFrenq)
			{
				highFrenqTerms.clear();
				maxFrenq = frenqTerms.get(i).getFrenq();
				highFrenqTerms.add(frenqTerms.get(i));
			}
			else if (frenqTerms.get(i).getFrenq() == maxFrenq)
			{
				highFrenqTerms.add(frenqTerms.get(i));
			}
			else
			{
			}
		}

		return highFrenqTerms;
	}
}
