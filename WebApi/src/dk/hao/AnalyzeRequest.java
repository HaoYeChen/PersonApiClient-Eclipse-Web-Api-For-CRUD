package dk.hao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequest {

	MatchEnum level;
	int id;

	public MatchEnum getLevel() {
		return level;
	}

	public int getId() {
		return id;
	}

	public AnalyzeRequest(String pathInfo) 
	{

		Matcher matcher = Pattern.compile("(?i)/Person/([0-9]+)").matcher(pathInfo);

		if (matcher.find()) 
		{
			level = MatchEnum.MatchPersonId;
			id = Integer.parseInt(matcher.group(1));
		} 
		else 
		{

			matcher = Pattern.compile("(?i)/Person[/]??$").matcher(pathInfo); // (?!) ignore case begynd, (?-i) slut f.eks "(?i)/Elev(?-i)[/]??$, /? betyder ingen eller 1 /
			if(matcher.find())
			{
				level = MatchEnum.MatchPerson;
			}
			else
			{
				level = MatchEnum.MatchNo;
			}
		}

	}

}
