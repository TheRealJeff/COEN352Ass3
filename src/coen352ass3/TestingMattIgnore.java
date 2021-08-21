package coen352ass3;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestingMattIgnore {
	static final int UNVISITED = 0;
	static final int VISITED = 1;
	public static CourseGraph createCGraph(BufferedReader file, CourseGraph G) throws IOException
	{
		int def_weight = 10; // weight is irrelevant to our assignment
		String line = null;
		StringTokenizer token;
		boolean undirected = false;
		int i, v1, v2, weight;
		String v1String, v2String;
		
		line = file.readLine();
		assert (line != null) :
		       "Unable to read number of vertices";
		while(line.charAt(0) == '#')
		assert (line = file.readLine()) != null : "Unable to read number of vertices";
		token = new StringTokenizer(line);
		int n = Integer.parseInt(token.nextToken());
		G.Init(n);
		for (i=0; i<n; i++)
			G.setMark(i, UNVISITED);
		assert (line = file.readLine()) != null :
		     "Unable to read graph type";
		if (line.charAt(0) == 'U')
		    undirected = true;
		else if (line.charAt(0) == 'D')
		    undirected = false;
		else assert false : "Bad graph type: " + line;
		
		while((line = file.readLine()) != null)
		{
			token = new StringTokenizer(line);
			v1String = token.nextToken();
			v2String = token.nextToken();
			v1 = 0;
			v2 = 0;
			boolean v1new = true;
			boolean v2new = true;
			for(int k = 0; k < G.courses.length; k++)
			{
				if(v1String == G.courses[k])
				{
					v1 = i;
					v1new = false;
				}
				else if (v2String == G.courses[k])
				{
					v2 = i;
					v2new = false;
				}
			}
			
			if(v1new)
			{
				v1 = G.numCourses;
				G.courses[v1] = v1String;
				G.numCourses++;
			}
			
			if(v2new)
			{
				v2 = G.numCourses;
				G.courses[v2] = v2String;
				G.numCourses++;
			}
			G.setEdge(v1, v2, def_weight);
		}
		return G;
	}
	
	
	static public void main(String[] args) throws IOException
	{
		
		
			 CourseGraph g = new CourseGraph();
			 BufferedReader f = new BufferedReader(new InputStreamReader(new FileInputStream("coen_course.gph")));

			createCGraph(f, g);
			
			for(int i = 0; i < g.numCourses; i++)
			{
				System.out.println(g.courses[i]);
			}
		
		 
	}
}
