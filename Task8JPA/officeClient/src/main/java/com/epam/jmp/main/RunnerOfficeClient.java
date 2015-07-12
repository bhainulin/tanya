package com.epam.jmp.main;

import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.Hibernate;

import com.epam.jmp.api.IEmployeeRemote;
import com.epam.jmp.api.IOfficeRemote;
import com.epam.jmp.api.IPersonalInfoRemote;
import com.epam.jmp.api.IProjectRemote;
import com.epam.jmp.api.IUnitRemote;
import com.epam.jmp.model.Employee;
import com.epam.jmp.model.Project;
import com.epam.jmp.model.Unit;


public class RunnerOfficeClient {
	
	private static String project_LookupName;
	private static String employee_LookupName;
	private static String office_LookupName;
	private static String personalInfo_LookupName;
	private static String unit_LookupName;
	
	private static Context context = null;
	
	
	static {
		project_LookupName = getLookupName("ProjectBean", "com.epam.jmp.api.IProjectRemote");
		employee_LookupName = getLookupName("EmployeeBean", "com.epam.jmp.api.IEmployeeRemote");
		office_LookupName = getLookupName("OfficeBean", "com.epam.jmp.api.IOfficeRemote");
		personalInfo_LookupName = getLookupName("PersonalInfoBean", "com.epam.jmp.api.IPersonalInfoRemote");
		unit_LookupName = getLookupName("UnitBean", "com.epam.jmp.api.IUnitRemote");
	
	}
	
	public static void main(String[] args) throws NamingException {
		IProjectRemote projectBean = (IProjectRemote)doLookup(project_LookupName);
		IEmployeeRemote employeeBean = (IEmployeeRemote)doLookup(employee_LookupName);
		IPersonalInfoRemote personalInfoBean = (IPersonalInfoRemote)doLookup(personalInfo_LookupName);
		IUnitRemote unitBean = (IUnitRemote)doLookup(unit_LookupName);
		IOfficeRemote officeBean = (IOfficeRemote)doLookup(office_LookupName);
			
		//projectRun(projectBean);
		//employeeRun(employeeBean);
		//officeRunChangeUnit(officeBean, employeeBean, unitBean);
		officeRunAssaignProject(officeBean, employeeBean, projectBean);
	}
	
	public static void projectRun(IProjectRemote projectBean ) throws NamingException{
		System.out.println("*************Project List*************");
		List<Project> all = projectBean.getList();
		for(Project ob : all){
			System.out.println(ob); 
		}
		
		System.out.println("*************Project Add*************");
		Project newProject = new Project(0, "testProject_New");
		projectBean.save(newProject);
		all = projectBean.getList();
		for(Project ob : all){
			System.out.println(ob); 
		}
		System.out.println("*******Project Update********");
		Project project = all.get(all.size()-1);
		int idProject = project.getId();
		project.setName("NEW test");
		projectBean.save(project);		
		System.out.println(projectBean.fetchById(idProject));
		
		System.out.println("*******Project Remove********");
		projectBean.remove(idProject);
		all = projectBean.getList();
		for(Project ob : all){
			System.out.println(ob); 
		}
		System.out.println("-------------------------------------------------------------");
	}
	
	public static void employeeRun(IEmployeeRemote employeeBean) throws NamingException{
		System.out.println("*************Employee List*************");
		List<Employee> all = employeeBean.getList();
		for(Employee ob : all){
			System.out.println(ob); 
		}
		
		System.out.println("*************Get Project By Employee*************");
		//Hibernate.initialize(employeeBean.getProjectsByEmployeeId(all.get(0).getId()));
		
		/**The reason is that when you use lazy load, the session is closed.
		    Don't use lazy load.
		    Set lazy=false in XML or Set @OneToMany(fetch = FetchType.EAGER) In annotation.
		    Use lazy load.
		    Set lazy=true in XML or Set @OneToMany(fetch = FetchType.LAZY) In annotation.
		    and add OpenSessionInViewFilter filter in your web.xml
		**/

		List<Project> projects = (List<Project>) employeeBean.getProjectsByEmployeeId(all.get(0).getId());
		
		for(Project ob : projects){
			System.out.println(ob); 
		}
		System.out.println("-------------------------------------------------------------");
	}
	
	public static void officeRunChangeUnit(IOfficeRemote officeBean, IEmployeeRemote employeeBean, IUnitRemote unitBean) throws NamingException{
		System.out.println("*************OFFICE ChangeUnit *************");
		Employee employee = employeeBean.fetchById(1);
		System.out.println(employee);
		System.out.println("Employee unit: "+employee.getUnit());
		System.out.println("All units: ");
		List<Unit> units= unitBean.getList();
		for(Unit u : units){
			System.out.println(u);
		}
		officeBean.addEmployeeToUnit(1, 2);
		System.out.println("Employee new unit: "+employeeBean.fetchById(1).getUnit());
	}
	
	public static void officeRunAssaignProject(IOfficeRemote officeBean, IEmployeeRemote employeeBean, IProjectRemote projectBean) throws NamingException{
		System.out.println("*************OFFICE AssaignProject*************");
		Employee employee = employeeBean.fetchById(1);
		System.out.println(employee);
		System.out.println("Employee projects: ");
		List<Project> list= (List<Project>) employee.getProjects();
		for(Project p : list){
			System.out.println(p);
		}
		List<Project> projects = projectBean.getList();
		System.out.println("All possible projects: " + projects);
		int projectID = projects.get(projects.size()-1).getId();
		
		officeBean.assignEmployeeForProject(employee.getId(), projectID);
		System.out.println("Updated Employee projects: ");
		list= (List<Project>) employeeBean.fetchById(1).getProjects();
		for(Project p : list){
			System.out.println(p);
		}
	}
	
	private static Object doLookup(String lookupName) {
	    //Context context = null;
	    Object bean = null;
        try {
          context = getContext();
          bean = context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
	}
	
    private static Context getContext() throws NamingException {
        Hashtable<String, Object> p = new Hashtable<String, Object>();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        p.put("jboss.naming.client.ejb.context", true);
        p.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447/");
        p.put(InitialContext.SECURITY_PRINCIPAL, "adminUser");
        p.put(InitialContext.SECURITY_CREDENTIALS, "password");
        p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        final Context context = new InitialContext(p);
        return context;
    }
    
    /**
     * 
     * @param beanName // The EJB bean implementation class name: beanName = "HelloWorldBean";
     * @param interfaceName // Fully qualified remote interface name: interfaceName = "com.epam.jmp.hello.HelloWorldBeanRemote";
     * @return
     */
    private static String getLookupName(String beanName, final String interfaceName) {
        /*
         * The module name is the JAR name of the deployed EJB without the .jar suffix.
         */
        String moduleName = "OfficeAtri-1.0-SNAPSHOT";
        // Create a look up string name
        String name = moduleName + "/" + beanName + "!" + interfaceName;
        return name;
    }
    
}
