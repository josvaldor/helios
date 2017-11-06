package org.josvaldor.prospero.helios.sunspot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.josvaldor.prospero.helios.Measurement;

public class Alpha {
	public String fileName = "./data/sunspot/alpha/SN_ms_tot_V2.0.csv";
	
    public List<Measurement> getMeasurementList() {
        String line = "";
        String delimeter = ";";
        List<Measurement> measurementList = new LinkedList<Measurement>();
        Measurement measurement = null;
        String[] stringArray;
        int year;
        int month;
        double number;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                stringArray = line.split(delimeter);
                year = Integer.parseInt(stringArray[0]);
                month = Integer.parseInt(stringArray[1]);    
                number = Double.parseDouble(stringArray[3]);
                measurement = new Measurement(new GregorianCalendar(year,month-1,0,0,0,0),"sunspot",number+""); 
                measurementList.add(measurement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return measurementList;
    }
}
