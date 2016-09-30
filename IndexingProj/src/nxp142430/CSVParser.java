package nxp142430;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

import au.com.bytecode.opencsv.CSVReader;


public class CSVParser {
	
	
	//DECLARATIONS**************************************************************************************************************************************************************/
	private static String csvfile = "";
	private static String dbfile = "data.db";
	private static String idfile = "id.ndx";
	private static String companyfile = "company.ndx";
	private static String drugfile = "drug.ndx";
	private static String trialfile = "trial.ndx";
	private static String patientfile = "patient.ndx";
	private static String dosagefile = "dosage.ndx";
	private static String readingfile = "reading.ndx";
	private static String blindfile = "dblind.ndx";
	private static String studyfile = "cstudy.ndx";
	private static String fundfile = "govtfund.ndx";
	private static String fdafile = "fda.ndx";
	
	final static byte double_blind_mask      = 8;    
	final static byte controlled_study_mask  = 4;    
	final static byte govt_funded_mask       = 2;    
	final static byte fda_approved_mask      = 1;
	
	static TreeMap<Integer,Long> addrList = new TreeMap<Integer,Long>();
	
	
	
	
	
	
	
	
	
	//PARSING**************************************************************************************************************************************************************/
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
	
	
	public TreeMap<Integer,Long> parse() {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter CSV Filename (Absolute Path) to be parse (without extension) :");
		csvfile = s.next();
		
		csvfile = csvfile+".csv";
		
		
		System.out.println("Parsing CSV to Binary ..... ");
		
		try {
			
			File f1 = new File(dbfile);
            RandomAccessFile raf = new RandomAccessFile(f1, "rw");
            CSVReader reader = new CSVReader(new FileReader(csvfile));
            
			String[] currLine = null;
			currLine = reader.readNext();
			
			while ((currLine = reader.readNext()) != null) {
	 
				addrList.put((Integer.parseInt(currLine[0])), raf.getFilePointer());
				
				String id=currLine[0];
				int idInt=Integer.parseInt(id);
				
				String company=currLine[1];
				int companyLen = company.length();
				
				String drugId=currLine[2];
				
				String trial=currLine[3];
				short trialShort=Short.parseShort(trial);
				
				String patients=currLine[4];
				short patientsShort=Short.parseShort(patients);
				
				String dosage=currLine[5];
				short dosageShort=Short.parseShort(dosage);
				
				String reading=currLine[6];
				float readingFloat=Float.parseFloat(reading);
			
				//System.out.println(currLine[7]+","+currLine[8]+","+currLine[9]+","+currLine[10]);
				byte commonByte = 0x00;	
				if(currLine[7].equalsIgnoreCase("TRUE")) 
					commonByte = (byte)(commonByte | double_blind_mask);
					
				
				
				if(currLine[8].equalsIgnoreCase("TRUE")) 
					commonByte = (byte)(commonByte | controlled_study_mask);
					
				
				if(currLine[9].equalsIgnoreCase("TRUE")) 
					commonByte = (byte)(commonByte | govt_funded_mask);
					
				
								
				if(currLine[10].equalsIgnoreCase("TRUE")) 
					commonByte = (byte)(commonByte | fda_approved_mask);
					
			
				
				
				//System.out.println(raf.getFilePointer());
				//System.out.println(raf.readByte()); 
								
			
				raf.writeInt(idInt);
				
				raf.writeByte(companyLen);
				raf.writeBytes(company);
				
				raf.writeBytes(drugId);
				raf.writeShort(trialShort);
				raf.writeShort(patientsShort);
				raf.writeShort(dosageShort);
				raf.writeFloat(readingFloat);
				raf.writeByte(commonByte);
				
			}
			
			 raf.close();
			 reader.close();
	 
		} catch (FileNotFoundException e) {
			System.out.println("No such file found, wrong filename !!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
			System.out.println("Parsing Complete !!");
			System.out.println("---------------------------------------");
		
			return addrList;
		 
	  }

	
	
	
	
	
	
	
	
	
	//INDEXING**************************************************************************************************************************************************************/
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
	
	
	public void createIndex(TreeMap<Integer,Long> idAddrList) {

		
		/* ID INDEX------------------------------------------------------------------------------------------------------ */
		try {
			
			File file = new File (idfile);
		    PrintWriter printWriter = new PrintWriter (file);
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();

				  String line = key + "=" + value;
				  printWriter.write(line);
				  printWriter.println();
				}
			printWriter.close();
			System.out.println("Index created for ID");
			
		} catch(Exception e) {
			e.getStackTrace();
		}

	}


	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */


	
	
	
	public void createIndexRest(TreeMap<Integer,Long> idAddrList) {

		
		/* COMPANY INDEX------------------------------------------------------------------------------------------------------ */
		try {
			
			File file = new File(companyfile);
			PrintWriter printWriter = new PrintWriter (file);
            CSVReader reader = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  reader = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = reader.readNext();
					
					while ((currLine = reader.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[1] + "=" + value;
							  printWriter.write(line);
							  printWriter.println();
						}
					}
				  
				}
			printWriter.close();
			System.out.println("Index created for Company");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		/* DRUG INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(drugfile);
			PrintWriter printWriterdrug = new PrintWriter (file);
            CSVReader readerdrug = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerdrug = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerdrug.readNext();
					
					while ((currLine = readerdrug.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[2] + "=" + value;
							  printWriterdrug.write(line);
							  printWriterdrug.println();
						}
					}
				  
				}
			printWriterdrug.close();
			System.out.println("Index created for Drug");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		

		/* TRIALS INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(trialfile);
			PrintWriter printWritertrial = new PrintWriter (file);
            CSVReader readertrial = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readertrial = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readertrial.readNext();
					
					while ((currLine = readertrial.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[3] + "=" + value;
							  printWritertrial.write(line);
							  printWritertrial.println();
						}
					}
				  
				}
			printWritertrial.close();
			System.out.println("Index created for Trial");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
/* PATIENTS INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(patientfile);
			PrintWriter printWriterpatient = new PrintWriter (file);
            CSVReader readerpatient = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerpatient = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerpatient.readNext();
					
					while ((currLine = readerpatient.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[4] + "=" + value;
							  printWriterpatient.write(line);
							  printWriterpatient.println();
						}
					}
				  
				}
			printWriterpatient.close();
			System.out.println("Index created for Patient");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		
/* DOSAGE INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(dosagefile);
			PrintWriter printWriterdosage = new PrintWriter (file);
            CSVReader readerdosage = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerdosage = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerdosage.readNext();
					
					while ((currLine = readerdosage.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[5] + "=" + value;
							  printWriterdosage.write(line);
							  printWriterdosage.println();
						}
					}
				  
				}
			printWriterdosage.close();
			System.out.println("Index created for Dosage");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		
		
		
/* READING INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(readingfile);
			PrintWriter printWriterreading = new PrintWriter (file);
            CSVReader readerreading = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerreading = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerreading.readNext();
					
					while ((currLine = readerreading.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[6] + "=" + value;
							  printWriterreading.write(line);
							  printWriterreading.println();
						}
					}
				  
				}
			printWriterreading.close();
			System.out.println("Index created for Reading");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		
/* DOUBLE BLIND INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(blindfile);
			PrintWriter printWriterblind = new PrintWriter (file);
            CSVReader readerblind = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerblind = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerblind.readNext();
					
					while ((currLine = readerblind.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[7] + "=" + value;
							  printWriterblind.write(line);
							  printWriterblind.println();
						}
					}
				  
				}
			printWriterblind.close();
			System.out.println("Index created for Double Blind");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		
		
		
/* CONTROLLED STUDY INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(studyfile);
			PrintWriter printWriterstudy = new PrintWriter (file);
            CSVReader readerstudy = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerstudy = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerstudy.readNext();
					
					while ((currLine = readerstudy.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[8] + "=" + value;
							  printWriterstudy.write(line);
							  printWriterstudy.println();
						}
					}
				  
				}
			printWriterstudy.close();
			System.out.println("Index created for Controlled Study");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		
		
		
		
/* GOVT FUNDED INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(fundfile);
			PrintWriter printWriterfund = new PrintWriter (file);
            CSVReader readerfund = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerfund = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerfund.readNext();
					
					while ((currLine = readerfund.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[9] + "=" + value;
							  printWriterfund.write(line);
							  printWriterfund.println();
						}
					}
				  
				}
			printWriterfund.close();
			System.out.println("Index created for Govt Funded");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		
		
		
		
/* FDA APPROVED INDEX------------------------------------------------------------------------------------------------------ */
		
		try {
			
			File file = new File(fdafile);
			PrintWriter printWriterfda = new PrintWriter (file);
            CSVReader readerfda = null;
		    
		    
			for(Map.Entry<Integer, Long> entry : idAddrList.entrySet()) {
				  Integer key = entry.getKey();
				  Long value = entry.getValue();
				  readerfda = new CSVReader(new FileReader(csvfile));
				  
				  String[] currLine = null;
				  currLine = readerfda.readNext();
					
					while ((currLine = readerfda.readNext()) != null) {
						String id=currLine[0];
						int idInt=Integer.parseInt(id);
						if (idInt == key) {
							  String line = currLine[10] + "=" + value;
							  printWriterfda.write(line);
							  printWriterfda.println();
						}
					}
				  
				}
			printWriterfda.close();
			System.out.println("Index created for FDA Approved");
			
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		

	}

	
	
	
	
	
	
	
	//SEARCHING*************************************************************************************************************************************************************/
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
	
	
	public void searchToID(int i, String operator) {
		
		Long addr;
		PharmaBean pb;
		ArrayList<PharmaBean> arrPB = new ArrayList<PharmaBean>();
		ArrayList<Long> addrList = new ArrayList<Long>();
		try {
			
			addrList = this.getAddress(i,operator);
			
			for(int x=0;x<addrList.size();x++) {
				pb = new PharmaBean();

				File f1 = new File(dbfile);
				RandomAccessFile raf = new RandomAccessFile(f1, "r");
				raf.seek(addrList.get(x));

				int id = raf.readInt();
				pb.setId(id);

				int comlen = raf.readByte();
				byte[] comp = new byte[comlen];
				raf.read(comp, 0, comlen);
				String cmpny = new String(comp);
				pb.setCompany(cmpny);

				byte[] drgid = new byte[6];
				raf.read(drgid, 0, 6);
				String drg = new String(drgid);
				pb.setDrug_id(drg);

				short trail = raf.readShort();
				pb.setTrials(trail);

				short patient = raf.readShort();
				pb.setPatients(patient);

				short dos = raf.readShort();
				pb.setDosage(dos);

				float read = raf.readFloat();
				pb.setReading(read);

				byte double_b;
				double_b = raf.readByte();
				//System.out.println((byte)(double_b & double_blind_mask));
				if((byte)(double_b & double_blind_mask)>(byte)0)
					pb.setDouble_blind(true);
				else 
					pb.setDouble_blind(false);


				if((byte)(double_b & controlled_study_mask)>(byte)0)
					pb.setControlled_study(true);
				else 
					pb.setControlled_study(false);


				if((byte)(double_b & govt_funded_mask)>(byte)0)
					pb.setGovt_funded(true);
				else 
					pb.setGovt_funded(false);


				if((byte)(double_b & fda_approved_mask)>(byte)0)
					pb.setFda_approved(true);
				else 
					pb.setFda_approved(false);
				
				arrPB.add(pb);
				raf.close();
			}
		    System.out.println("For ID: " + operator+" "+i);
		    this.printWholeList(arrPB);
		    System.out.println("");System.out.println("");System.out.println("");
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	public void searchToString(String inputStr, int option, String operator) {
		Long addr;
		PharmaBean pb;
		ArrayList<PharmaBean> arrPB = new ArrayList<PharmaBean>();
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			
			if(option == 1)
				addrList = this.getAddressToString(companyfile,inputStr,operator);
			
			if(option == 2)
				addrList = this.getAddressToString(drugfile,inputStr,operator);
			
			for(int i=0;i<addrList.size();i++) {
				
				pb = new PharmaBean();

				File f1 = new File(dbfile);
				RandomAccessFile raf = new RandomAccessFile(f1, "r");
				raf.seek(addrList.get(i));

				int id = raf.readInt();
				pb.setId(id);

				int comlen = raf.readByte();
				byte[] comp = new byte[comlen];
				raf.read(comp, 0, comlen);
				String cmpny = new String(comp);
				pb.setCompany(cmpny);

				byte[] drgid = new byte[6];
				raf.read(drgid, 0, 6);
				String drg = new String(drgid);
				pb.setDrug_id(drg);

				short trail = raf.readShort();
				pb.setTrials(trail);

				short patient = raf.readShort();
				pb.setPatients(patient);

				short dos = raf.readShort();
				pb.setDosage(dos);

				float read = raf.readFloat();
				pb.setReading(read);

				byte double_b;
				double_b = raf.readByte();
				//System.out.println((byte)(double_b & double_blind_mask));
				if((byte)(double_b & double_blind_mask)>(byte)0)
					pb.setDouble_blind(true);
				else 
					pb.setDouble_blind(false);


				if((byte)(double_b & controlled_study_mask)>(byte)0)
					pb.setControlled_study(true);
				else 
					pb.setControlled_study(false);


				if((byte)(double_b & govt_funded_mask)>(byte)0)
					pb.setGovt_funded(true);
				else 
					pb.setGovt_funded(false);


				if((byte)(double_b & fda_approved_mask)>(byte)0)
					pb.setFda_approved(true);
				else 
					pb.setFda_approved(false);


				arrPB.add(pb);
				raf.close();
			}
		    
			if(option == 1)
			System.out.println("For Company Name: " +operator+" " +inputStr.toUpperCase());
			
			if(option == 2)
				System.out.println("For Drug ID: " +operator+" " + inputStr.toUpperCase());
			
			this.printWholeList(arrPB);
			System.out.println("");System.out.println("");System.out.println("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	public void searchToShort(Short inputNum, int option, String operator) {
		Long addr;
		PharmaBean pb;
		ArrayList<PharmaBean> arrPB = new ArrayList<PharmaBean>();
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			
			if(option == 1)
				addrList = this.getAddressToShort(trialfile,inputNum,operator);
			
			if(option == 2)
				addrList = this.getAddressToShort(patientfile,inputNum,operator);
			
			if(option == 3)
				addrList = this.getAddressToShort(dosagefile,inputNum,operator); 
			
			
			for(int i=0;i<addrList.size();i++) {
				
				pb = new PharmaBean();

				File f1 = new File(dbfile);
				RandomAccessFile raf = new RandomAccessFile(f1, "r");
				raf.seek(addrList.get(i));

				int id = raf.readInt();
				pb.setId(id);

				int comlen = raf.readByte();
				byte[] comp = new byte[comlen];
				raf.read(comp, 0, comlen);
				String cmpny = new String(comp);
				pb.setCompany(cmpny);

				byte[] drgid = new byte[6];
				raf.read(drgid, 0, 6);
				String drg = new String(drgid);
				pb.setDrug_id(drg);

				short trail = raf.readShort();
				pb.setTrials(trail);

				short patient = raf.readShort();
				pb.setPatients(patient);

				short dos = raf.readShort();
				pb.setDosage(dos);

				float read = raf.readFloat();
				pb.setReading(read);

				byte double_b;
				double_b = raf.readByte();
				//System.out.println((byte)(double_b & double_blind_mask));
				if((byte)(double_b & double_blind_mask)>(byte)0)
					pb.setDouble_blind(true);
				else 
					pb.setDouble_blind(false);


				if((byte)(double_b & controlled_study_mask)>(byte)0)
					pb.setControlled_study(true);
				else 
					pb.setControlled_study(false);


				if((byte)(double_b & govt_funded_mask)>(byte)0)
					pb.setGovt_funded(true);
				else 
					pb.setGovt_funded(false);


				if((byte)(double_b & fda_approved_mask)>(byte)0)
					pb.setFda_approved(true);
				else 
					pb.setFda_approved(false);


				arrPB.add(pb);
				raf.close();
			}
		    
			
			
			if(option == 1)
				System.out.println("For Trials: " +operator+" " + inputNum);
			if(option == 2)
				System.out.println("For Patients: " +operator+" " + inputNum);
			if(option == 3)
				System.out.println("For Dosage_mg: " +operator+" " + inputNum);
				
			this.printWholeList(arrPB);
			System.out.println("");System.out.println("");System.out.println("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	
/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	public void searchToReading(Float inputNum, String operator) {
		Long addr;
		PharmaBean pb;
		ArrayList<PharmaBean> arrPB = new ArrayList<PharmaBean>();
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			
				addrList = this.getAddressToReading(readingfile,inputNum,operator); 
			
			
			for(int i=0;i<addrList.size();i++) {
				
				pb = new PharmaBean();

				File f1 = new File(dbfile);
				RandomAccessFile raf = new RandomAccessFile(f1, "r");
				raf.seek(addrList.get(i));

				int id = raf.readInt();
				pb.setId(id);

				int comlen = raf.readByte();
				byte[] comp = new byte[comlen];
				raf.read(comp, 0, comlen);
				String cmpny = new String(comp);
				pb.setCompany(cmpny);

				byte[] drgid = new byte[6];
				raf.read(drgid, 0, 6);
				String drg = new String(drgid);
				pb.setDrug_id(drg);

				short trail = raf.readShort();
				pb.setTrials(trail);

				short patient = raf.readShort();
				pb.setPatients(patient);

				short dos = raf.readShort();
				pb.setDosage(dos);

				float read = raf.readFloat();
				pb.setReading(read);

				byte double_b;
				double_b = raf.readByte();
				//System.out.println((byte)(double_b & double_blind_mask));
				if((byte)(double_b & double_blind_mask)>(byte)0)
					pb.setDouble_blind(true);
				else 
					pb.setDouble_blind(false);


				if((byte)(double_b & controlled_study_mask)>(byte)0)
					pb.setControlled_study(true);
				else 
					pb.setControlled_study(false);


				if((byte)(double_b & govt_funded_mask)>(byte)0)
					pb.setGovt_funded(true);
				else 
					pb.setGovt_funded(false);


				if((byte)(double_b & fda_approved_mask)>(byte)0)
					pb.setFda_approved(true);
				else 
					pb.setFda_approved(false);


				arrPB.add(pb);
				raf.close();
			}
		    
			
			
				System.out.println("For Reading : " +operator+" " + inputNum);
				
			this.printWholeList(arrPB);
			System.out.println("");System.out.println("");System.out.println("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	
	
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	public void searchToBool(Boolean input, int option) {
		Long addr;
		PharmaBean pb;
		ArrayList<PharmaBean> arrPB = new ArrayList<PharmaBean>();
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			
			if(option == 1)
				addrList = this.getAddressToBool(blindfile,input);
			
			if(option == 2)
				addrList = this.getAddressToBool(studyfile,input);
			
			if(option == 3)
				addrList = this.getAddressToBool(fundfile,input); 
			
			if(option == 4)
				addrList = this.getAddressToBool(fdafile,input);
			
			
			for(int i=0;i<addrList.size();i++) {
				
				pb = new PharmaBean();

				File f1 = new File(dbfile);
				RandomAccessFile raf = new RandomAccessFile(f1, "r");
				raf.seek(addrList.get(i));

				int id = raf.readInt();
				pb.setId(id);

				int comlen = raf.readByte();
				byte[] comp = new byte[comlen];
				raf.read(comp, 0, comlen);
				String cmpny = new String(comp);
				pb.setCompany(cmpny);

				byte[] drgid = new byte[6];
				raf.read(drgid, 0, 6);
				String drg = new String(drgid);
				pb.setDrug_id(drg);

				short trail = raf.readShort();
				pb.setTrials(trail);

				short patient = raf.readShort();
				pb.setPatients(patient);

				short dos = raf.readShort();
				pb.setDosage(dos);

				float read = raf.readFloat();
				pb.setReading(read);

				byte double_b;
				double_b = raf.readByte();
				//System.out.println((byte)(double_b & double_blind_mask));
				if((byte)(double_b & double_blind_mask)>(byte)0)
					pb.setDouble_blind(true);
				else 
					pb.setDouble_blind(false);


				if((byte)(double_b & controlled_study_mask)>(byte)0)
					pb.setControlled_study(true);
				else 
					pb.setControlled_study(false);


				if((byte)(double_b & govt_funded_mask)>(byte)0)
					pb.setGovt_funded(true);
				else 
					pb.setGovt_funded(false);


				if((byte)(double_b & fda_approved_mask)>(byte)0)
					pb.setFda_approved(true);
				else 
					pb.setFda_approved(false);


				arrPB.add(pb);
				raf.close();
			}
		    
			
			String in = input+"";
			in = in.toUpperCase();
			if(option == 1)
				System.out.println("For Double Blind Flag : " + in);
			if(option == 2)
				System.out.println("For Controlled Study Flag: " + in);
			if(option == 3)
				System.out.println("For Govt Funded Flag: " + in);
			if(option == 4)
				System.out.println("For FDA Approved Flag: " + in);
				
			this.printWholeList(arrPB);
			System.out.println("");System.out.println("");System.out.println("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	
	
	
	
	
	//FETCHING ADDRESS******************************************************************************************************************************************************/
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
	

	private ArrayList<Long> getAddress(int i, String operator) throws IOException {
		
		Long address = new Long(0);
		Integer id = new Integer(0);
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			FileInputStream fis = new FileInputStream(idfile);
			DataInputStream ds = new DataInputStream(fis);
		    BufferedReader br = new BufferedReader(new InputStreamReader(ds));
		    
			String currLine = null;
			
			while ((currLine = br.readLine()) != null) {
				
				String[] res = currLine.split("=");
				id = Integer.parseInt(res[0]);
				
				if(operator.equals("=")) {
					if (id == i) { 
						address = Long.parseLong(res[1]);
						addrList.add(address);
					}
				} else if(operator.equals("!=")) {
					if (id != i){ 
						address = Long.parseLong(res[1]);
						addrList.add(address);
					}
				}else if(operator.equals(">=")) {
					if (id >= i){ 
						address = Long.parseLong(res[1]);
						addrList.add(address);
					}
				}else if(operator.equals("<=")) {
					if (id <= i) { 
						address = Long.parseLong(res[1]);
						addrList.add(address);
					}
				}else if(operator.equals(">")) {
					if (id > i) { 
						address = Long.parseLong(res[1]);
						addrList.add(address);
					}
				}else if(operator.equals("<")) {
					if (id < i) { 
						address = Long.parseLong(res[1]);
						addrList.add(address);
					}
				}
					
			}
			
			br.close();
			ds.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Index File not found !!");
			System.out.println("Start Again after creating index files !!");
			System.exit(0);
		}
		return addrList;
		
	}
	
	
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	private ArrayList<Long> getAddressToString(String path, String name, String operator) throws NumberFormatException, IOException {
		
		Long address = new Long(0);
		String field = new String();
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			FileInputStream fis = new FileInputStream(path);
			DataInputStream ds = new DataInputStream(fis);
		    BufferedReader br = new BufferedReader(new InputStreamReader(ds));
		    
			String currLine = null;
			
			while ((currLine = br.readLine()) != null) {
				
				String[] res = currLine.split("=");
				field = res[0];
				
				if(operator.equals("=")) {
					if (field.equalsIgnoreCase(name)) {
						address = Long.parseLong(res[1]);
						addrList.add(address);	
					}
				} else if(operator.equals("!=")) {
					if (!field.equalsIgnoreCase(name)) {
						address = Long.parseLong(res[1]);
						addrList.add(address);	
					}
				}		
			}
			br.close();
			ds.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Index File not found !!");
			System.out.println("Start Again after creating index files !!");
			System.exit(0);
		}
		
		return addrList;
	}

	
	
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	private ArrayList<Long> getAddressToShort(String path, Short num, String operator) throws NumberFormatException, IOException {
		
		Long address = new Long(0);
		Short field;
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			DataInputStream ds = new DataInputStream(fis);
		    BufferedReader br = new BufferedReader(new InputStreamReader(ds));
		    
			String currLine = null;
			
			while ((currLine = br.readLine()) != null) {
				
				String[] res = currLine.split("=");
				field = new Short(Short.parseShort(res[0]));
				
				if(operator.equals("=")) {
					if (field.equals(num)) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				} else if(operator.equals("!=")) {
					if (!field.equals(num)) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals(">=")) {
					if (field >= num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals("<=")) {
					if (field <= num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals(">")) {
					if (field > num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals("<")) {
					if (field < num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}
				
					
			}
			
			br.close();
			ds.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Index File not found !!");
			System.out.println("Start Again after creating index files !!");
			System.exit(0);
		}
		
		return addrList;
	}

	
	
	
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	private ArrayList<Long> getAddressToReading(String path, Float num, String operator) throws NumberFormatException, IOException {
		
		Long address = new Long(0);
		Float field;
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			DataInputStream ds = new DataInputStream(fis);
		    BufferedReader br = new BufferedReader(new InputStreamReader(ds));
		    
			String currLine = null;
			
			while ((currLine = br.readLine()) != null) {
				
				String[] res = currLine.split("=");
				field = new Float(Float.parseFloat(res[0]));
				
				if(operator.equals("=")) {
					if (field.equals(num)) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				} else if(operator.equals("!=")) {
					if (!field.equals(num)) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals(">=")) {
					if (field >= num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals("<=")) {
					if (field <= num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals(">")) {
					if (field > num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}else if(operator.equals("<")) {
					if (field < num) {
						address = Long.parseLong(res[1]);
						addrList.add(address);
						
					}
				}
					
			}
			
			br.close();
			ds.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Index File not found !!");
			System.out.println("Start Again after creating index files !!");
			System.exit(0);
		}
		
		return addrList;
	}

	
	
/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */

	
	private ArrayList<Long> getAddressToBool(String path, Boolean flag) throws NumberFormatException, IOException {
		
		Long address = new Long(0);
		Boolean field;
		ArrayList<Long> addrList = new ArrayList<Long>();
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			DataInputStream ds = new DataInputStream(fis);
		    BufferedReader br = new BufferedReader(new InputStreamReader(ds));
		    
			String currLine = null;
			
			while ((currLine = br.readLine()) != null) {
				
				String[] res = currLine.split("=");
				field = new Boolean(Boolean.parseBoolean(res[0]));
				
				if (field.equals(flag)) {
					address = Long.parseLong(res[1]);
					addrList.add(address);
					
				}	
			}			
			br.close();
			ds.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Index File not found !!");
			System.out.println("Start Again after creating index files !!");
			System.exit(0);
		}
		
		return addrList;
	}

	
	
	
	
	
	
	//PRINTING**************************************************************************************************************************************************************/
	
	
	private void printWholeList(ArrayList<PharmaBean> arrPB) {
		
		
		if(arrPB.size()>0) {
			System.out.println("ID     "+"Drug ID  "+"Trials  "+"Patients  "+"Dosage  "+"Reading  "+"DoubleBlind  "+"ContolledStudy  "+"GovtFunded  "+"FDAApproved  "+"Company                     ");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			for(int i=0;i<arrPB.size();i++) 
					System.out.println(arrPB.get(i).getId()+"    "+arrPB.get(i).getDrug_id()+"    "+arrPB.get(i).getTrials()+"     "+arrPB.get(i).getPatients()+"      "+arrPB.get(i).getDosage()+"     "+arrPB.get(i).getReading()+"     "+arrPB.get(i).isDouble_blind()+"         "+arrPB.get(i).isControlled_study()+"            "+arrPB.get(i).isGovt_funded()+"        "+arrPB.get(i).isFda_approved()+"          "+arrPB.get(i).getCompany());
			
			System.out.println();
			System.out.println("Total Records Found : "+arrPB.size());
			System.out.println();
		} else 
			System.out.println("Sorry ! NO RECORDS FOUND !!");
	}
	
	/*  ------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
	

}
