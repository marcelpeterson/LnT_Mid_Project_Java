import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	
	ArrayList<Karyawan> listKaryawan = new ArrayList<>();
	
	Integer countManager = 0;
	Integer countSupervisor = 0;
	Integer countAdmin = 0;
	
	Long baseGajiManager = (long) 8000000; 
	Long baseGajiSupervisor = (long) 6000000; 
	Long baseGajiAdmin = (long) 4000000; 
	
	Long bonusManager = (long) (baseGajiManager * 0.1);
	Long bonusSupervisor = (long) (baseGajiSupervisor * 0.075);
	Long bonusAdmin = (long) (baseGajiAdmin * 0.05);
	
	public Main() {
		while(true) {
			int choice = Menu(scan);
			scan.nextLine();
			switch(choice) {
			case 1:
				Case1();
				break;
			case 2:
				Case2();
				break;
			case 3:
				Case3();
				break;
			case 4:
				Case4();
				break;
			case 5:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	
	public void Case1() {
		String nama;
		String kelamin;
		String jabatan;
		String kode = GenerateKode();
		Long gaji = (long) 0;
		
		do {
			System.out.print("Input nama karyawan [>= 3]: ");
			nama = scan.nextLine();
		}while(nama.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki - laki | Perempuan] (Case Sensitive): ");
			kelamin = scan.nextLine();
		}while(!kelamin.equals("Laki - laki") && !kelamin.equals("Perempuan"));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
		}while(!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin"));
		
		if(jabatan.equals("Manager")) {
			gaji = baseGajiManager;
		} else if(jabatan.equals("Supervisor")) {
			gaji = baseGajiSupervisor;
		} else if(jabatan.equals("Admin")) {
			gaji = baseGajiAdmin;
		}
		
		listKaryawan.add(new Karyawan(kode, nama, kelamin, jabatan, gaji));
		
		System.out.println("Berhasil menambahkan karyawan dengan id: " + kode);
		BonusGaji(jabatan);
		
		Collections.sort(listKaryawan, new Comparator<Karyawan>() {
			public int compare(Karyawan k1, Karyawan k2) {
				return String.valueOf(k1.nama).compareTo(k2.nama);
			}
		});
		
		System.out.println("ENTER to return");
		scan.nextLine();
		
	}
	
	public void Case2() {
		Display();
		System.out.println("ENTER to return");
		scan.nextLine();
	}
	
	public void Case3() {
		Display();
		
		int choice;
		System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
		choice = scan.nextInt();
	    scan.nextLine();
	    
	    if (choice <= 0 || choice > listKaryawan.size()) {
	        System.out.println("Invalid choice. ENTER to return");
	        scan.nextLine();
	        return;
	    }
	    
	    int index = choice - 1;
		
		String getKode = listKaryawan.get(index).kode;
		
		String nama;
		String kelamin;
		String jabatan;
		
		do {
			System.out.print("Input nama karyawan [>= 3]: ");
			nama = scan.nextLine();
		}while(nama.length() < 3 && !nama.equals("0"));
		
		if(!nama.equals("0")) {
			listKaryawan.get(index).nama = nama;
		}

		do {
			System.out.print("Input jenis kelamin [Laki - laki | Perempuan] (Case Sensitive): ");
			kelamin = scan.nextLine();
		}while(!kelamin.equals("Laki - laki") && !kelamin.equals("Perempuan") && !kelamin.equals("0"));
		
		if(!kelamin.equals("0")) {
			listKaryawan.get(index).kelamin = kelamin;
		}
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
		}while(!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin") && !jabatan.equals("0"));
		
		if(!jabatan.equals("0")) {
			listKaryawan.get(index).jabatan = jabatan;
		}
		
		if(jabatan.equals("Manager")) {
			listKaryawan.get(index).gaji = baseGajiManager;
		} else if(jabatan.equals("Supervisor")) {
			listKaryawan.get(index).gaji = baseGajiSupervisor;
		} else if(jabatan.equals("Admin")) {
			listKaryawan.get(index).gaji = baseGajiAdmin;
		}

		System.out.println("Berhasil mengupdate karyawan dengan id " + getKode);
		BonusGaji(jabatan);
		
		Collections.sort(listKaryawan, new Comparator<Karyawan>() {
			public int compare(Karyawan k1, Karyawan k2) {
				return String.valueOf(k1.nama).compareTo(k2.nama);
			}
		});
		
		System.out.println("ENTER to return");
		scan.nextLine();

	}
	
	public void Case4() {
		Display();
		
		int choice;
		System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
		choice = scan.nextInt();
	    scan.nextLine();
	    
	    if (choice <= 0 || choice > listKaryawan.size()) {
	        System.out.println("Invalid choice. ENTER to return");
	        scan.nextLine();
	        return;
	    }
	    
	    int index = choice - 1;
		
		String getKode = listKaryawan.get(index).kode;
		
		listKaryawan.remove(index);
		
		System.out.println("Karyawan dengan kode " + getKode + " berhasil dihapus");
		System.out.println("ENTER to return");
		scan.nextLine();
	}
	
	public void BonusGaji(String jabatan) {
	    if(jabatan.equals("Manager")) {
	        ArrayList<String> karyawanKodeList = new ArrayList<String>();

	        for(int i = 0; i < listKaryawan.size(); i++) {
	            if(listKaryawan.get(i).jabatan.equals("Manager") && !listKaryawan.get(i).hasReceivedBonus) {
	                karyawanKodeList.add(listKaryawan.get(i).kode);
	            }
	        }

	        if((karyawanKodeList.size() - 1) % 3 == 0 && karyawanKodeList.size() > 1) {
	            karyawanKodeList.remove(karyawanKodeList.size() - 1);

	            for(String kodeList : karyawanKodeList) {
	                int index = ValidateKode(kodeList);
	                if(index != -1 && listKaryawan.get(index).gaji != baseGajiManager + bonusManager) {
	                    listKaryawan.get(index).gaji += bonusManager;
	                    listKaryawan.get(index).hasReceivedBonus = true;
	                }
	            }
	            PrintKode(jabatan, karyawanKodeList);
	        }
	    } else if(jabatan.equals("Supervisor")) {
	        ArrayList<String> karyawanKodeList = new ArrayList<String>();

	        for(int i = 0; i < listKaryawan.size(); i++) {
	            if(listKaryawan.get(i).jabatan.equals("Supervisor") && !listKaryawan.get(i).hasReceivedBonus) {
	                karyawanKodeList.add(listKaryawan.get(i).kode);
	            }
	        }

	        if((karyawanKodeList.size() - 1) % 3 == 0 && karyawanKodeList.size() > 1) {
	            karyawanKodeList.remove(karyawanKodeList.size() - 1);

	            for(String kodeList : karyawanKodeList) {
	                int index = ValidateKode(kodeList);
	                if(index != -1 && listKaryawan.get(index).gaji != baseGajiSupervisor + bonusSupervisor) {
	                    listKaryawan.get(index).gaji += bonusSupervisor;
	                    listKaryawan.get(index).hasReceivedBonus = true;
	                }
	            }
	            PrintKode(jabatan, karyawanKodeList);
	        }
	    } else if(jabatan.equals("Admin")) {
	        ArrayList<String> karyawanKodeList = new ArrayList<String>();

	        for(int i = 0; i < listKaryawan.size(); i++) {
	            if(listKaryawan.get(i).jabatan.equals("Admin") && !listKaryawan.get(i).hasReceivedBonus) {
	                karyawanKodeList.add(listKaryawan.get(i).kode);
	            }
	        }

	        if((karyawanKodeList.size() - 1) % 3 == 0 && karyawanKodeList.size() > 1) {
	            karyawanKodeList.remove(karyawanKodeList.size() - 1);

	            for(String kodeList : karyawanKodeList) {
	                int index = ValidateKode(kodeList);
	                if(index != -1 && listKaryawan.get(index).gaji != baseGajiAdmin + bonusAdmin) {
	                    listKaryawan.get(index).gaji += bonusAdmin;
	                    listKaryawan.get(index).hasReceivedBonus = true;
	                }
	            }
	            PrintKode(jabatan, karyawanKodeList);
	        }
	    }
	}

	public int ValidateKode(String kode) {
	    HashMap<String, Integer> kodeToIndexMap = new HashMap<>();
	    for (int i = 0; i < listKaryawan.size(); i++) {
	        kodeToIndexMap.put(listKaryawan.get(i).kode, i);
	    }

	    Integer index = kodeToIndexMap.get(kode);
	    return index != null ? index : -1;
	}
	
	public void Display() {
		System.out.println("|---|-----------------|------------------------------|--------------|-------------|-------------|");
		System.out.println("|No |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin |Jabatan      |Gaji Karyawan|");
		System.out.println("|---|-----------------|------------------------------|--------------|-------------|-------------|");
		for(int i = 0; i < listKaryawan.size(); i++) {
			System.out.printf("|%3s|%17s|%30s|%14s|%13s|%13s|\n", (i + 1), listKaryawan.get(i).kode, listKaryawan.get(i).nama, listKaryawan.get(i).kelamin, listKaryawan.get(i).jabatan, listKaryawan.get(i).gaji);
		}
		System.out.println("|---|-----------------|------------------------------|--------------|-------------|-------------|");
	}
	
	public void PrintKode(String jabatan, ArrayList<String> kodeList) {
		if(jabatan.equals("Manager")) {
			System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
			for(int i = 0; i < kodeList.size(); i++) {
				System.out.print(kodeList.get(i));
				if(i != kodeList.size() - 1) {
					System.out.print(", ");
				}
			}
		} else if(jabatan.equals("Supervisor")) {
			System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
			for(int i = 0; i < kodeList.size(); i++) {
				System.out.print(kodeList.get(i));
				if(i != kodeList.size() - 1) {
					System.out.print(", ");
				}
			}
		} else if(jabatan.equals("Admin")) {
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
			for(int i = 0; i < kodeList.size(); i++) {
				System.out.print(kodeList.get(i));
				if(i != kodeList.size() - 1) {
					System.out.print(", ");
				}
			}
		}
		System.out.println("");
	}
	
	public String GenerateKode() {
		StringBuilder generateKode = new StringBuilder();
		
		for(int i = 0; i < 2; i++) {
			Random random = new Random();
			char randomChar = (char) (random.nextInt(26) + 'A');
			generateKode.append(randomChar);
		}
		
		generateKode.append("-");
		
		for(int i = 0; i < 4; i++) {
			generateKode.append((int) (Math.random() * 10));
		}
		
		return generateKode.toString();
	}
	
	public int Menu(Scanner scan) {
		System.out.println("Data Karyawan PT. ChipiChapa");
		System.out.println("=================================");
		System.out.println("1. Insert Data Karyawan");
		System.out.println("2. View Data Karyawan");
		System.out.println("3. Update Data Karyawan");
		System.out.println("4. Delete Data Karyawan");
		System.out.println("5. Exit");
		System.out.print(">> ");
		return scan.nextInt();
	}
}
