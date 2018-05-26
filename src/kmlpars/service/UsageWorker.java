package kmlpars.service;

import kmlpars.beans.Lang;
import kmlpars.beans.Usage;

/**
 *
 * @author dmitry
 */
public class UsageWorker {
    
    public static void addUsage(String name){
        Usage usage = new Usage();
        usage.setId(Constants.USAGES.get(Constants.USAGES.size()-1).getId()+1);
        usage.getTitle().setName(name);
        usage.getTitle().setLang("ru");
        Constants.USAGES.add(usage);
        
        for (Usage USAGE : Constants.USAGES) {
            System.out.println(USAGE);
        }
    }

    static Usage getUsageById(int id) {
        for (int i = 0; i < Constants.USAGES.size(); i++){
            if (Constants.USAGES.get(i).getId() == id){
                return Constants.USAGES.get(i);
            }
        }
        return null;
    }

    public static Usage getUsageByName(String string) {
        for (int i = 0; i < Constants.USAGES.size(); i++){
            if (Constants.USAGES.get(i).getTitle().getName().equals(string)){
                return Constants.USAGES.get(i);
            }
        }
        return null;
    }
    
}
