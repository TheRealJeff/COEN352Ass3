package coen352ass3;

public class CourseGraph extends Graphm{
	public String [] courses;
	// store the course corresponding to each course
	public CourseGraph()
	{
		//Init(def_size);
		courses = new String [def_size];
	}
	public CourseGraph(int n)
	{
		Init(n);
		courses = new String [n];
	}
	
	public void clear()
	{
		// clear all marks from CourseGraph object
		for(int i = 0; i < this.n(); i++)
		{
			this.setMark(i, UNVISITED);
		}
	}
	
	public String getPrerequisitePath(String courseCode) { 
		this.clear(); // set all to UNVISITED
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

			if (prereqs != null) {
				// push all prerequisites to stack
				for(int i = 0; i < prereqs.length; i++)
				{
					stackToOutput.push(prereqs[i]);
					stack.push(prereqs[i]);
				}
			}
		}
		// print all elements in stack
		while(stackToOutput.length() > 0)
		{
			toReturn += stackToOutput.pop();
			toReturn += " ";
		}
		return toReturn;
	}

	public String [] getPrerequisite(String courseCode)
	{
		// clear visited array
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
			if (courses[i] != null) {
				if (courses[i].equals(courseCode))
					indexOfInput = i;
			}
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

	public static int getIndex(String course) throws Exception {
		// needed to convert strings in file to integers
		int index;
		switch (course)
		{
			case "MATH204":
				index = 0;
				break;

			case "COEN243":
				index = 1;
				break;

			case "COEN212":
				index = 2;
				break;

			case "COEN231":
				index = 3;
				break;

			case "COEN311":
				index = 4;
				break;

			case "COEN313":
				index = 5;
				break;

			case "COEN346":
				index = 6;
				break;

			case "COEN352":
				index = 7;
				break;

			case "ENGR290":
				index = 8;
				break;

			case "ELEC311":
				index = 9;
				break;

			case "COEN317":
				index = 10;
				break;

			case "COEN320":
				index = 11;
				break;

			case "SOEN341":
				index = 12;
				break;

			case "ELEC372":
				index = 13;
				break;

			case "COEN244":
				index = 14;
				break;

			case "COEN366":
				index = 15;
				break;

			case "ENGR301":
				index = 16;
				break;

			case "ENGR371":
				index = 17;
				break;

			case "COEN390":
				index = 18;
				break;

			case "COEN466":
				index = 19;
				break;

			case "COEN451":
				index = 20;
				break;

			case "COEN316":
				index = 21;
				break;

			case "COEN413":
				index = 22;
				break;

			case "COEN424":
				index = 23;
				break;

			case "COEN432":
				index = 24;
				break;

			case "COEN434":
				index = 25;
				break;

			case "COEN415":
				index = 26;
				break;

			case "COEN433":
				index = 27;
				break;

			case "COEN421":
				index = 28;
				break;

			case "COEN447":
				index = 29;
				break;

			case "COEN422":
				index = 30;
				break;

			case "COEN448":
				index = 31;
				break;

			case "COEN490":
				index = 32;
				break;

			case "COEN446":
				index = 33;
				break;

			default:
				System.out.println(course);
				throw new Exception("Invalid course");
		}
		return index;
	}
}
