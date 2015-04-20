import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		
		/** Ellenorzes az elso argumentum szerint
		 * Az elso argumentum 0 eseten a Realtime tesztel�st hivja meg,
		 * 1 eseten a 2. argumentumban megjelolt filet olvassa es annak parancsai szerint vegzi a tesztelest.
		 * ha egyik sem, a program kilep.
		 */
		if(args[0].equals(new String("0"))){							//Real time teszteset		
			System.out.println("Real-time teszt...");
			
			
			Scanner parancsRead = new Scanner(System.in);				//valtozok inicializalasa
			String parancs;
			do {														//A ciklus addig olvassa a parancsokat, amig az 'exitProto' parancs be nem erkezik
				System.out.println("Adja meg a parancs kodjat:");
				if(parancsRead.hasNext())
		            parancs = parancsRead.next();						//Console olvasasa
				else { parancs = new String("exitProto");}
				
				vegrehajt(parancs);										//a parancs feldolgozasa
				
				
			}while (!parancs.equals("exitProto"));
			
			
		}
		else if(args[0].equals(new String("1"))){						//Filebol olvasasos teszteset
			System.out.println("File teszt...");
			
			BufferedReader br = new BufferedReader(new FileReader(args[1]));	//a m�sodik argumentum, mint f�ljn�v olvas�sa
		    try {
		        
		        String line = br.readLine();
		        while(line!= null){										//ciklus, am�g van sor a f�jlban.
		        	vegrehajt(line);
		        	line = br.readLine();
		        }
		    } finally {
		        br.close();
		    }
			
			
		}
		else
			System.out.println("Hibas argumentumok!");					//Hibas elso argumentumnal kilep a program.
	}

	/**A parancsok feldolgozasaert felelos metodus
	 * A parameterkent kapott parancs szerinte meghivja a megfelelo utasitasokat.
	 */
	private static void vegrehajt(String parancs) {
		// TODO Auto-generated method stub
		System.out.println("Parancs jott.");
		
		/* V�gtelen if-else kapocs
		 * A k�l�nb�z� parancsok lekezel�se
		 */
		if(parancs.equals("")){
			
		}else
		if(parancs.equals("")){
			
		}
	}

}
