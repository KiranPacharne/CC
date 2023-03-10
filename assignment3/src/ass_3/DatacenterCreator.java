package ass_3;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class DatacenterCreator {
public static Datacenter createDatacenter(String name) {
List<Host> hostList = new ArrayList<Host>();
List<Pe> peList = new ArrayList<Pe>();
int mips = 1000;
peList.add(new Pe(0, new PeProvisionerSimple(mips)));
int hostId = 0;
int ram = 2048; //host memory (MB)
long storage = 1000000; //host storage
int bw = 10000;
hostList.add(
new Host(
hostId,
new RamProvisionerSimple(ram),
new BwProvisionerSimple(bw),
storage,
peList,
new VmSchedulerTimeShared(peList)
)
); // This is our first machine
String arch = "x86"; // system architecture
String os = "Linux"; // operating system
String vmm = "Xen";
double time_zone = 10.0; // time zone this resource located
double cost = 3.0; // the cost of using processing in this resource
double costPerMem = 0.05; // the cost of using memory in this resource
double costPerStorage = 0.1; // the cost of using storage in this resource
double costPerBw = 0.1; // the cost of using bw in this resource
LinkedList<Storage> storageList = new LinkedList<Storage>();
DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);
Datacenter datacenter = null;
try {
datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList,
0);
} catch (Exception e) {
e.printStackTrace();
}
return datacenter;
}
}