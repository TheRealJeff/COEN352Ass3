package coen352ass3;

public class CourseGraph extends Graphm{
	public String [] courses;
	// store the course corresponding to each course
	public CourseGraph()
	{
		Init(def_size);
		courses = new String [def_size];
	}
	public CourseGraph(int n)
	{
		Init(n);
		courses = new String [n];
	}
	
	public void clear()
	{
		for(int i = 0; i < this.n(); i++)
		{
			this.setMark(i, UNVISITED);
		}
	}
	
	public String getPrerequisitePath(String courseCode) { // Recursive topological sort
		this.clear();
		ADTStack <String> stack = new AStack <String> (100);
		ADTStack <String> stackToOutput = new AStack <String> (100);
		String toReturn = "";
		// we need two stacks to search all paths and for output
		stack.push(courseCode);
		stackToOutput.push(courseCode);
		
		while(stack.length() > 0)
		{
			String v = stack.pop();
			
			String [] prereqs = this.getPrerequisiteHelp(v);
			
			for(int i = 0; i < prereqs.length; i++)
			{
				stackToOutput.push(prereqs[i]);
				stack.push(prereqs[i]);
			}
			
			
			
			
		}
		while(stackToOutput.length() > 0)
		{
			toReturn += stackToOutput.pop();
			toReturn += " ";
		}
		return toReturn;
	}

	public String [] getPrerequisite(String courseCode)
	{
		this.clear();
		return this.getPrerequisiteHelp(courseCode);
	}
	
	public String [] getPrerequisiteHelp(String courseCode)
	{
		int prCount = 0;
		int indexOfInput = -1;
		String[] prereqs = new String [this.n() - 1]; // allocate max space at first
		
		// find node representing given course
		for(int i = 0; i < this.n(); i++)
		{
			if(courses[i] == courseCode)
				indexOfInput = i;
		}
		if(indexOfInput == -1)
			return null; // course is not in graph
		
		// find prerequisites for argument course
		for(int k = 0; k < this.n(); k++)
		{
			if(matrix[k][indexOfInput] != 0)
			{
				this.setMark(k, VISITED);
				prereqs[prCount] = courses[k];
				prCount++;
			}
		}
		
		// create an array of correct size
		String [] toReturn = new String[prCount];
		for(int j = 0; j < prCount; j++)
		{
			toReturn[j] = prereqs[j];
		}
		
		return toReturn;
		
	}
	
	static public void main(String [] args)
	{
		CourseGraph g = new CourseGraph ();
		g.courses[0] = "Cal 1";
		g.courses[1] = "Cal 2";
		g.courses[2] = "Chem 1";
		g.courses[3] = "Chem 2";
		g.courses[4] = "Mechanics";
		g.courses[5] = "Waves";
		g.courses[6] = "E and M";
		
		g.setEdge(0, 1, 1);
		g.setEdge(1, 6, 1);
		g.setEdge(2, 3, 1);
		g.setEdge(4, 5, 1);
		g.setEdge(5, 6, 1);
		System.out.println("OUTPUT:" + g.getPrerequisitePath("E and M"));
	}
}
