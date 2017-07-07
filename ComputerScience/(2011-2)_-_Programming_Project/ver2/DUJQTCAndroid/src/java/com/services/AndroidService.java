/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dataPack.Message;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Erik Andres Regla Torres
 */
@WebService(serviceName = "AndroidService")
public class AndroidService {

    /*
     * 
     * pide cantidad de noticias, y listado de las mismas.
     * para asi hacer notificaciones estilo feisbuk..shia :D
     * se obtiene cantidad. listo
     * 
     * peticion de noticas por clasificacion, eso se hace en ws.
     * 
     * se piden las ultimas 10 bajo un metodo. listo weon.
     * 
     * las que vengan despues se piden especificando el inicio y cuantas mas,
     * de ser posible claro.
     * 
     * deben de haber metodos para poder hacerlo de forma independiente de la clasificacion.
     * 
     * ha de existir una cache, en la cual alojar las weas.
     * lista tu wea
     */
    @WebMethod(operationName = "test")
    public int test() {
        return 1;
    }

    @WebMethod(operationName = "testXml")
    public String testXml() throws Exception {
        Message message = new Message(0, 0, 1, 12, 2012);
        message.add("Neko 1", "kynku");
        return message.serialize();
    }
    
    

    @WebMethod(operationName = "requestMultiFilteredNotice")
    public String requestMultiFilteredNotice(@WebParam(name = "type") String type,
            @WebParam(name = "index") int index) throws Exception {
        Cache.load();
        Gson gson = new Gson();
        boolean[] contenido = gson.fromJson(type, boolean[].class);
        
        for (int i = Cache.messageCache.size() - 1; i >= 0; i--) {
            if (contenido[Cache.messageCache.get(i).type]) {
                    System.out.println("Mensaje!");
                if (index == 0) {
                    return Cache.messageCache.get(i).serialize();
                } else {
                    index--;
                }
            }
        }
        return "error";
    }


    @WebMethod(operationName = "requestFilteredNotice")
    public String requestFilteredNotice(@WebParam(name = "type") int type,
            @WebParam(name = "index") int index) throws Exception {
        Cache.load();
        for (int i = Cache.messageCache.size() - 1; i >= 0; i--) {
            if (Cache.messageCache.get(i).type == type) {
                if (index == 0) {
                    return Cache.messageCache.get(i).serialize();
                } else {
                    index--;
                }
            }
        }
        return "error";
    }

    @WebMethod(operationName = "requestNotice")
    public String requestNotice(@WebParam(name = "index") int index) throws Exception {
        Cache.load();
        for (int i = Cache.messageCache.size() - 1; i >= 0; i--) {
            if (index == 0) {
                return Cache.messageCache.get(i).serialize();
            } else {
                index--;
            }
        }
        return "error";
    }

    @WebMethod(operationName = "requestCalendarNotice")
    public String requestCalendarNotice(@WebParam(name = "index") int index, @WebParam(name = "year") int year, @WebParam(name = "month") int month, @WebParam(name = "day") int day) {
        Cache.load();
        for (int i = Cache.messageCache.size() - 1; i >= 0; i--) {
            if (Cache.messageCache.get(i).type == 10 && Cache.messageCache.get(i).year == year && Cache.messageCache.get(i).month == month && Cache.messageCache.get(i).day == day) {
                if (index == 0) {
                    return Cache.messageCache.get(i).serialize();
                } else {
                    index--;
                }
            }
        }
        return "error";
    }

    @WebMethod(operationName = "requestYearList")
    public String requestYearList() {
        Cache.load();
        ArrayList<Integer> years = new ArrayList<Integer>();
        for (Message m : Cache.messageCache) {
            if (m.type == 10) {
                if (!years.contains(m.year)) {
                    years.add(m.year);
                }
            }
        }
        int[] sorter = new int[years.size()];

        for (int i = 0; i < years.size(); i++) {
            sorter[i] = years.get(i);
        }

        Arrays.sort(sorter);

        int[] sorterCount = new int[sorter.length];

        for (Message m : Cache.messageCache) {
            for (int i = 0; i < sorter.length; i++) {
                if (m.year == sorter[i]) {
                    sorterCount[i]++;
                    break;
                }
            }
        }

        Message messages = new Message(0, 10, 0, 0, 0);

        for (int i = 0; i < sorter.length; i++) {
            messages.add(""+sorter[i],""+ sorterCount[i]);
        }

        return messages.serialize();
    }

    @WebMethod(operationName = "requestYearMonthList")
    public String requestYearMonthList(@WebParam(name = "year") int year) {
        Cache.load();
        ArrayList<Integer> months = new ArrayList<Integer>();
        for (Message m : Cache.messageCache) {
            if (m.type == 10) {
                if (!months.contains(m.month) && m.year == year) {
                    months.add(m.month);
                }
            }
        }
        int[] sorter = new int[months.size()];

        for (int i = 0; i < months.size(); i++) {
            sorter[i] = months.get(i);
        }

        Arrays.sort(sorter);

        int[] sorterCount = new int[sorter.length];

        for (Message m : Cache.messageCache) {
            for (int i = 0; i < sorter.length; i++) {
                if (m.month == sorter[i] && m.year == year) {
                    sorterCount[i]++;
                    break;
                }
            }
        }

        Message messages = new Message(0, 10, 0, 0, year);

        for (int i = 0; i < sorter.length; i++) {
            messages.add(""+ sorter[i], ""+sorterCount[i]);
        }

        return messages.serialize();
    }

    @WebMethod(operationName = "requestYearMonthDayList")
    public String requestYearMonthDayList(@WebParam(name = "year") int year, @WebParam(name = "month") int month) {
        Cache.load();
        ArrayList<Integer> days = new ArrayList<Integer>();
        for (Message m : Cache.messageCache) {
            if (m.type == 10) {
                if (!days.contains(m.day) && m.year == year && m.month == month) {
                    days.add(m.day);
                }
            }
        }
        int[] sorter = new int[days.size()];

        for (int i = 0; i < days.size(); i++) {
            sorter[i] = days.get(i);
        }

        Arrays.sort(sorter);

        int[] sorterCount = new int[sorter.length];

        for (Message m : Cache.messageCache) {
            for (int i = 0; i < sorter.length; i++) {
                if (m.day == sorter[i] && m.year == year && m.month == month) {
                    sorterCount[i]++;
                    break;
                }
            }
        }

            Message messages = new Message(0, 10, 0, month, year);

        for (int i = 0; i < sorter.length; i++) {
            messages.add(""+sorter[i], ""+sorterCount[i]);
        }

        return messages.serialize();
    }
//
////    @WebMethod(operationName = "requestFilteredNotices")
////    public String requestFilteredNotices(@WebParam(name = "type") int type, @WebParam(name = "from") int from, @WebParam(name = "range") int range) throws Exception {
////        ArrayList<HashMap> outputData = Cache.getCachedMessages();
////        ArrayList returnData = new ArrayList();
////        
////        if((range < 0) || (range >= outputData.size())) return AndroConverter.convertTo(new Transporter());
////        
////        for (int i = outputData.size() - 1 - from; (i == 0) && (range != 0); i--) {
////            if ((Integer)outputData.get(i).get("type") == type) {
////                returnData.add(outputData.get(i));
////                range--;
////            }
////        }
////        return null;
////    }    
//    @WebMethod(operationName = "requestFilteredNotices")
//    public String requestFilteredNotices(@WebParam(name = "type") int type, @WebParam(name = "from") int from, @WebParam(name = "range") int range) throws Exception {
//
//        NoticeArray outputData = Cache.messageCache;
//
//        if (type >= 0 && type < 6) {
//            outputData = Cache.messageAlumnosCache;
//            System.out.println("Set ALcache " + outputData.size());
//        } else if (type >= 6 && type < 10) {
//            outputData = Cache.messageExAlumnosCache;
//            System.out.println("Set EXcache " + outputData.size());
//        } else if (type == 10) {
//            outputData = Cache.messageCalendarCache;
//            System.out.println("Set CALcache " + outputData.size());
//        }
//
//        System.out.println("type=" + type + " -- range=" + range + " -- from=" + from);
//        Storage returnData = new Storage();
//
//        if ((range < 0) || (range >= outputData.size())) {
//            return returnData.saveToString();
//        }
//
//        System.out.println("type=" + type + " -- range=" + range + " -- from=" + from);
//        for (int i = outputData.size() - 1; (i >= 0) && (range != 0); i--) {
//
//            Mapper adding = new Mapper();
//            System.out.println("type=" + type + " -- range=" + range + " -- from=" + from + " -- i=" + i);
//
//            if (outputData.get(i).type == type) {
//                //returnData.add(new Mapper((HashMap<String, String>)outputData.get(i)));
//                if (from-- <= 0) {
//                    String[] keyVector = new String[1];
//                    keyVector = outputData.get(i).keySet().toArray(keyVector);
//                    for (String s : keyVector) {
//                        adding.put(s, outputData.get(i).get(s));
//                    }
//
//                    returnData.add(adding);
//                    range--;
//                    System.out.println("data included");
//                }
//            }
//        }
//        return returnData.saveToString();
//    }
////    
////    @WebMethod(operationName = "requestFilteredNotices")
////    public String requestFilteredNotices(@WebParam(name = "type") int type, @WebParam(name = "from") int from, @WebParam(name = "range") int range) throws Exception {
////        ArrayList<HashMap> outputData = Cache.getCachedMessages();
////        ArrayList returnData = new ArrayList();
////        
////        if((range < 0) || (range >= outputData.size())) return AndroConverter.convertTo(new Transporter());
////        
////        for (int i = outputData.size() - 1 - from; (i == 0) && (range != 0); i--) {
////            if ((Integer)outputData.get(i).get("type") == type) {
////                returnData.add(outputData.get(i));
////                range--;
////            }
////        }
////        return null;
////    }
//
//    @WebMethod(operationName = "requestNotices")
//    public String requestNotices(@WebParam(name = "from") int from, @WebParam(name = "range") int range) throws Exception {
//        NoticeArray outputData = Cache.messageCache;
//        Storage returnData = new Storage();
//
//        if ((range < 0) || (range >= outputData.size())) {
//            return "null";
//        }
//
//        Mapper adding = new Mapper();
//        for (int i = outputData.size() - 1 - from; (i >= 0) && (range != 0); i--) {
//            //returnData.add(new Mapper((HashMap<String, String>)outputData.get(i)));
//
//            String[] keyVector = new String[1];
//            keyVector = outputData.get(i).keySet().toArray(keyVector);
//            for (String s : keyVector) {
//                adding.put(s, outputData.get(i).get(s));
//            }
//
//            returnData.add(adding);
//            range--;
//            System.out.println("data included");
//        }
//        return returnData.saveToString();
//    }
//
//    /**
//     */
//    @WebMethod(operationName = "requestNoticesListByType")
//    public int requestNoticesListByType(@WebParam(name = "type") int type) {
//        return Cache.messageCount[type];
//    }
////    @WebMethod(operationName = "requestNoticesListByType")
////    public int requestNoticesListByType(@WebParam(name = "type") int type) {
////        int number = 0;
////        try {
////            ArrayList<HashMap> messages = Message.loadPublishedMessages();
////            for (HashMap m : messages) {
////            if ((Integer)m.get("type") == type) {
////                    number++;
////                }
////            }
////        } catch (Exception ex) {
////            Logger.getLogger("Error en lectura de mensajes. \n" + AndroidService.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////        return number;
////    }
//
//    @WebMethod(operationName = "requestNoticesList")
//    public int requestNoticesList() {
//        return Cache.messageCache.size();
//    }
//
//    @WebMethod(operationName = "reload")
//    public void reload() {
//        Cache.load();
//    }
//
//    @WebMethod(operationName = "requestNoticesListMap")
//    public String requestNoticesListMap() {
//        String outputString = "";
//        int[] resultsVector = Cache.messageCount;
//        Storage out = new Storage();
//        try {
//            HashMap<String, String> output = new HashMap<String, String>();
//            for (int i = 0; i < 11; i++) {
//                output.put(i + "", resultsVector[i] + "");
//                System.out.println(resultsVector[i] + "");
//            }
//            out.add(new Mapper(output));
//            outputString = out.saveToString();
//
//        } catch (Exception ex) {
//            Logger.getLogger("Error en lectura de mensajes. \n" + AndroidService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return outputString;
//    }
//
//    @WebMethod(operationName = "getYearMap")
//    public String getYearMap() throws Exception {
//        HashMap<String, String> yearMap = new HashMap<String, String>();
//        Storage sto = new Storage();
//
//        //enlista los años
//        for (NoticeMap m : Cache.messageCalendarCache) {
//            //si contiene el año, agrega, sino lo crea
//            String currentYear = m.get("year");
//            if (yearMap.containsKey(currentYear)) {
//                int newValue = Integer.parseInt(yearMap.get(currentYear));
//                yearMap.put(currentYear, Integer.toString(++newValue));
//            } else {
//                yearMap.put(currentYear, "1");
//            }
//        }
//        String[] out = new String[1];
//        out = yearMap.keySet().toArray(out);
//        int[] pros = new int[out.length];
//
//        for (int i = 0; i < out.length; i++) {
//            pros[i] = Integer.parseInt(out[i]);
//        }
//
//        Arrays.sort(pros);
//
//        for (int i = 0; i < out.length; i++) {
//            Mapper map = new Mapper();
//            map.put("year", "" + pros[i]);
//            map.put("messages", "" + yearMap.get(pros + ""));
//            sto.add(map);
//        }
//
//        return sto.saveToString();
////        HashMap<String, Integer> Yea
////        
////        Set keys = yearMap.keySet();
////        
////
////        String[] keyVector = new String[1];
////        keyVector = (String[]) yearMap.keySet().toArray(keyVector);
////        Integer[] intVector = new Integer[1];
////        ArrayList<Integer> temp = new ArrayList<Integer>();
////        for (String s : keyVector) {
////            temp.add(Integer.parseInt(s));
////        }
////        intVector = temp.toArray(intVector);
////        
////        Arrays.sort(intVector);
////        return null;
//    }
//
//    @WebMethod(operationName = "getMonthMap")
//    public String getMonthMap(@WebParam(name = "year") String year) throws Exception {
//        HashMap<String, String> monthMap = new HashMap<String, String>();
//        Storage sto = new Storage();
//
//        //enlista los años
//        for (NoticeMap m : Cache.messageCalendarCache) {
//            //si contiene el año, agrega, sino lo crea
//            String currentMonth = m.get("month");
//            if (m.get("year").equals(year)) {
//                if (monthMap.containsKey(currentMonth)) {
//                    int newValue = Integer.parseInt(monthMap.get(currentMonth));
//                    monthMap.put(currentMonth, Integer.toString(++newValue));
//                } else {
//                    monthMap.put(currentMonth, "1");
//                }
//            }
//        }
//        String[] out = new String[1];
//        out = monthMap.keySet().toArray(out);
//        int[] pros = new int[out.length];
//
//        for (int i = 0; i < out.length; i++) {
//            pros[i] = Integer.parseInt(out[i]);
//        }
//
//        Arrays.sort(pros);
//
//        System.out.println(pros.length);
//        System.out.println(pros);
//
//        for (int i = 0; i < pros.length; i++) {
//            Mapper map = new Mapper();
//            System.out.println("natsu: " + pros[i]);
//            map.put("month", "" + pros[i]);
//            map.put("messages", "" + monthMap.get(pros + ""));
//            sto.add(map);
//        }
//
//        return sto.saveToString();
////        HashMap<String, Integer> Yea
////        
////        Set keys = yearMap.keySet();
////        
////
////        String[] keyVector = new String[1];
////        keyVector = (String[]) yearMap.keySet().toArray(keyVector);
////        Integer[] intVector = new Integer[1];
////        ArrayList<Integer> temp = new ArrayList<Integer>();
////        for (String s : keyVector) {
////            temp.add(Integer.parseInt(s));
////        }
////        intVector = temp.toArray(intVector);
////        
////        Arrays.sort(intVector);
//
////        return null;
//    }
//
//    @WebMethod(operationName = "getDayMap")
//    public String getDayMap(@WebParam(name = "year") String year, @WebParam(name = "month") String month) throws Exception {
//        HashMap<String, String> dayMap = new HashMap<String, String>();
//        Storage sto = new Storage();
//
//        //enlista los años
//        for (NoticeMap m : Cache.messageCalendarCache) {
//            //si contiene el año, agrega, sino lo crea
//            String currentDay = m.get("day");
//            System.out.println("current day01:" + m.get("year") + "/" + m.get("month") + "/" + currentDay);
//            System.out.println("current day02:" + year + "/" + month);
//            if (m.get("year").equals(year) && m.get("month").equals(month)) {
//                System.out.println("current day:" + currentDay);
//                if (dayMap.containsKey(currentDay)) {
//                    int newValue = Integer.parseInt(dayMap.get(currentDay));
//                    dayMap.put(currentDay, Integer.toString(++newValue));
//                } else {
//                    dayMap.put(currentDay, "1");
//                }
//            }
//        }
//        String[] out = new String[0];
//        out = dayMap.keySet().toArray(out);
//        int[] pros = new int[out.length];
//
//        for (int i = 0; i < out.length; i++) {
//            pros[i] = Integer.parseInt(out[i]);
//        }
//
//        Arrays.sort(pros);
//
//        System.out.println(pros);
//
//        for (int i = 0; i < out.length; i++) {
//            System.out.println("dia: " + pros[i]);
//            Mapper map = new Mapper();
//            map.put("day", "" + pros[i]);
//            map.put("messages", "" + dayMap.get(pros + ""));
//            sto.add(map);
//        }
//
//        return sto.saveToString();
////        HashMap<String, Integer> Yea
////        
////        Set keys = yearMap.keySet();
////        
////
////        String[] keyVector = new String[1];
////        keyVector = (String[]) yearMap.keySet().toArray(keyVector);
////        Integer[] intVector = new Integer[1];
////        ArrayList<Integer> temp = new ArrayList<Integer>();
////        for (String s : keyVector) {
////            temp.add(Integer.parseInt(s));
////        }
////        intVector = temp.toArray(intVector);
////        
////        Arrays.sort(intVector);
//
////        return null;
//    }
//
//    @WebMethod(operationName = "requestNoticesListYearMap")
//    public String requestNoticesListYearMap(@WebParam(name = "from") int from, @WebParam(name = "year") String year, @WebParam(name = "month") String month, @WebParam(name = "day") String day) throws Exception {
//
//        NoticeArray outputData = Cache.messageCache;
//
//        System.out.println("Set CALcache " + outputData.size());
//
//        Storage returnData = new Storage();
//        int range = 1;
//        int type = 10;
//
//        for (int i = outputData.size() - 1; (i >= 0) && (range != 0); i--) {
//
//            Mapper adding = new Mapper();
//
//            if (outputData.get(i).type == type) {
//                //returnData.add(new Mapper((HashMap<String, String>)outputData.get(i)));
//                if (from-- <= 0) {
//                    String[] keyVector = new String[1];
//                    keyVector = outputData.get(i).keySet().toArray(keyVector);
//                    for (String s : keyVector) {
//                        adding.put(s, outputData.get(i).get(s));
//                    }
//
//                    returnData.add(adding);
//                    range--;
//                    System.out.println("data included");
//                }
//            }
//        }
//        return returnData.saveToString();
//    }
//    //get map para dias y era D:

    @WebMethod(operationName = "requestMessageCount")
    public String requestMessageCount() {
        Gson gson = new Gson();
        return gson.toJson(Cache.messageCount);
    }
}