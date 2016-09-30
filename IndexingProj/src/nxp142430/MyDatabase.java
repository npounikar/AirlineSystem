package nxp142430;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;



public class MyDatabase {

	public static void main(String[] args) {
		
		//initialization
		Scanner scan = new Scanner(System.in);
		TreeMap<Integer,Long> addr = new TreeMap<Integer,Long>();
		System.out.println("Project 2 : File and Indexing");
		System.out.println("==============================");
		System.out.println();
		System.out.println();
		String flagparse = "", flagindex="", input="",operator="", kontinue="", kont="";
		int opt = 0;
		short trial = 0, patient = 0, dos = 0;
		float read;
		Boolean t = new Boolean(true);
 		Boolean f = new Boolean(false);
 		
 		
 		
		//input from user to parse the file--------------------------------------------------------------------------------------------------------------------------------
			CSVParser parser = new CSVParser();
			addr = parser.parse();
			

			//input from user to create indexes--------------------------------------------------------------------------------------------------------------------------------
			do {
				System.out.println("Do you want to create the index files for the domains(Y/N) ?");
				flagindex = scan.next();
			}while(!(flagindex.equalsIgnoreCase("Y") || flagindex.equalsIgnoreCase("N")));
			

			//create index files
			if(flagindex.equalsIgnoreCase("N")) {
				do {
					System.out.println("Have you already created it(Y/N) ?");
					kontinue = scan.next();
				}while(!(kontinue.equalsIgnoreCase("Y") || kontinue.equalsIgnoreCase("N")));
				
				if (kontinue.equalsIgnoreCase("N")) {
					System.out.println("TO Proceed further, Index Files needed, Hence creating index files !!");
					System.out.println("");
					flagindex = "Y";
				}
				
			}
			
			if(flagindex.equalsIgnoreCase("Y")) {
				System.out.println("---------CREATING INDEX FILES-----------");
				parser.createIndex(addr);
				parser.createIndexRest(addr);
				System.out.println("--------All Index Files Created----------");
				System.out.println();
			}	
				
				//input from user to select the search criteria--------------------------------------------------------------------------------------------------------------
			    scan.nextLine();
				do {
					System.out.println("Select Search Criteria, from following search menu : ");
					System.out.println("1. By ID");
					System.out.println("2. By Company Name");
					System.out.println("3. By Drug ID");
					System.out.println("4. By Trials");
					System.out.println("5. By Patients");
					System.out.println("6. By Dosage (in mg)");
					System.out.println("7. By Reading");
					System.out.println("8. By Double Blind FLAG");
					System.out.println("9. By Controlled Study FLAG");
					System.out.println("10. By Govt Funded FLAG");
					System.out.println("11. By FDA Approved FLAG");
					opt = Integer.parseInt(scan.nextLine());
				
				
				if(opt == 1) {
					do {
						
						System.out.println("Please provide ID ranging from 1 to 1000 :  ");
						opt = Integer.parseInt(scan.nextLine());
					}while(!(opt >= 1 && opt <=1000));
					
					do {
					System.out.println("Please provide the operator( =, !=, <, >, <=, >= )  :  ");
					operator = scan.next();
					}while(!(operator.equals("=") || operator.equals("!=") || operator.equals("<=") || operator.equals(">=") || operator.equals(">") || operator.equals("<")));
					printLines();
					parser.searchToID(opt,operator);
					
					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
					
				} else if(opt == 2 || opt == 3) {
					
					if( opt == 2) {
						System.out.println("Please provide the Company Name :  ");
						input = scan.nextLine(); 
					} else if( opt == 3) {
						System.out.println("Please provide the Drug ID :  ");
						input = scan.nextLine(); 
					}
						do {
						System.out.println("Please provide the operator( =, != )  :  ");
						operator = scan.nextLine();
						}while(!(operator.equals("=") || operator.equals("!=")));
						
					printLines();
					if( opt == 2) 
						parser.searchToString(input,1,operator);
					else if( opt == 3) 
						parser.searchToString(input,2,operator);

					
					
					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
					
					
				} else if(opt == 4 || opt == 5 || opt == 6) {
					
					if(opt == 4) {
						System.out.println("Please provide the number of Trials :  ");
						trial = scan.nextShort();
					} else if(opt == 5) {
						System.out.println("Please provide the number of  Patients :  ");
						patient = scan.nextShort();
					} else if(opt == 6) {
						System.out.println("Please provide the number of Dosage  :  ");
						dos = scan.nextShort();
					}
						
						do {
						System.out.println("Please provide the operator( =, !=, <, >, <=, >= )  :  ");
						operator = scan.next();
						}while(!(operator.equals("=") || operator.equals("!=") || operator.equals("<=") || operator.equals(">=") || operator.equals(">") || operator.equals("<")));
					
					printLines();	
					if(opt == 4) 
						parser.searchToShort(trial,1,operator);
					else if(opt == 5) 
						parser.searchToShort(patient,2,operator);
					else if(opt == 6) 
						parser.searchToShort(dos,3,operator);
					
					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
						
				} else if(opt == 7) {
					
						System.out.println("Please provide the Reading  :  ");
						read = scan.nextFloat();
						do {
						System.out.println("Please provide the operator( =, !=, <, >, <=, >= )  :  ");
						operator = scan.next();
						}while(!(operator.equals("=") || operator.equals("!=") || operator.equals("<=") || operator.equals(">=") || operator.equals(">") || operator.equals("<")));
					
					printLines();
					parser.searchToReading(read,operator);

					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
					
					
					
				} else if(opt == 8) {
					
					do {
						System.out.println("Please provide Double Blind FLAG(T/F) :  ");
						input = scan.next();
					}while(!(input.equalsIgnoreCase("T") || input.equalsIgnoreCase("F")));
					
					printLines();
					if(input.equalsIgnoreCase("T"))
						parser.searchToBool(t,1);
					if(input.equalsIgnoreCase("F"))
						parser.searchToBool(f,1);

					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
					
					
					
				} else if(opt == 9) {
					
					do {
						System.out.println("Please provide Controlled Study FLAG(T/F) :  ");
						input = scan.next();
					}while(!(input.equalsIgnoreCase("T") || input.equalsIgnoreCase("F")));
					
					printLines();
					if(input.equalsIgnoreCase("T"))
						parser.searchToBool(t,2);
					if(input.equalsIgnoreCase("F"))
						parser.searchToBool(f,2);

					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
					
					
				} else if(opt == 10) {
					
					do {
						System.out.println("Please provide Govt Funded FLAG(T/F) :  ");
						input = scan.next();
					}while(!(input.equalsIgnoreCase("T") || input.equalsIgnoreCase("F")));
					
					printLines();
					if(input.equalsIgnoreCase("T"))
						parser.searchToBool(t,3);
					if(input.equalsIgnoreCase("F"))
						parser.searchToBool(f,3);

					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
					
					
				} else if(opt == 11) {
					
					do {
						System.out.println("Please provide FDA Approved FLAG(T/F) :  ");
						input = scan.next();
					}while(!(input.equalsIgnoreCase("T") || input.equalsIgnoreCase("F")));
					
					printLines();
					if(input.equalsIgnoreCase("T"))
						parser.searchToBool(t,4);
					if(input.equalsIgnoreCase("F"))
						parser.searchToBool(f,4);

					do {
						System.out.println("Do you want to coninue(Y/N) ?");
						kont = scan.nextLine();
					}while(!(kont.equalsIgnoreCase("Y") || kont.equalsIgnoreCase("N")));
					
					if(kont.equalsIgnoreCase("N")) {
						System.out.println("BYE BYE");
						System.exit(0);
					}
					
				}	
			}while((!(opt >= 1 && opt <=11))||(kont.equalsIgnoreCase("Y")));
		
		
			
	}
	
	
	//*---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	

	private static void printLines() {
		System.out.println("===============================================================================================================================================");
		System.out.println("");System.out.println("");System.out.println("");
		System.out.println("Following are the Query Search Results : ");
		System.out.println("-----------------------------------------");
		System.out.println("");System.out.println("");
		
		
	} 

}
