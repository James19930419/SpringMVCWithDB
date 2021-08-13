package com.example.SpringMVC2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller will map all javascript uri to the corresponding java functions
 * then it can return to the new url link by return some String
 *
 * */

/**
 * 1:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * ModelMap 就是NSDictionary 用来从java里向JSP传参。
 * ModelMap.addAttribute("key",object) 或 ModelMap.put("key",object)是一样的，不过put可传递NULL
 *
 * jsp调取时通过${"key"}来实现
 *
 * 2:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @RequestParam 可以从JSP调取参数，
 * 如果 @RequestParam(value="age"，required=true)Integer age1 则age参数为空是报错
 *
 * 注意只能取出有指针的object，所以不能用int(用Integer)
 *
 * 需要赋值一个默认值，写成如下的形式：
 * @RequestParam(value = "age", required = false, defaultValue = "0") int age1)
 *
 * 可以通过 href="update-todo?id=${todo.catcode}" 里面的 ?key=value 来向@RequestParam 传参
 *
 * 也可以在任何表格等用到POST 或 GET的部件里传参，参数名写在 name='key' 里， 参数值写在 value='value'里即可
 *
 * 只要发送请求的时候URL上有 ? key=value 即可用 @RequestParam 取到 value,
 * 所以只要注意浏览器的url就知道能取出来什么参数了！！！！！！！
 * 3:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   @Autowired
 *   attribute 相当于自动写gettter setter
 *
 * 4:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @RequestMapping(value = "/", method = RequestMethod.GET)
 * 可以在url尾缀里有对应的字段时（这里是/ 也就是根目录），并且是GET/POST 请求时 trigger下面的方法
 * 注意尾缀要和 JSP 中的 href 或者 browser上的URL尾缀相同
 */



// controller 需要下面这些东西
@SessionAttributes({"custno","custname","cdep","nyears","savtype","errMsg"}) //session的参数好像一直保存着
// 用的时候 String iid = (String) model.get("id");//这个可以从session里调用id
@RequestMapping
@Controller
public class MainController {

    public static final String ADD_TODO = "add-todo";
    public static final String UPDATE_TODO = "update-todo";
    public static final String DELETE_TODO = "delete-todo";
    public static final String LIST_ALL_ITEMS = "see-todo";
    DAOService dataBase;//数据库操作函数句柄

//    @Autowired
//    Categoryservice service;
//The autowire allows you to implement a dependency injection technique in Spring MVC.
//Notice, that we never instantiated an object for Category service and yet we were able to
//use it.
    @Autowired//autowired 可以省略getter/setter，建议将property都搞成autowired
PDOService connect;
    //a mapping when someone enters file
//    @RequestMapping(value = "/MainPage", method = RequestMethod.GET)
//    public String showCategorypage(ModelMap model,@RequestParam(defaultValue = "") String id) throws ClassNotFoundException, SQLException{
//        dataBase = new DAOService(connect.connect());
//        model.addAttribute("todos", dataBase.selectAllCategoryTable());
//        List<CategoryClass> filteredTodos = new ArrayList<CategoryClass>();
//        filteredTodos = (List) model.get("todos");
//        model.put("id",filteredTodos.get(0).getCatcode());
//        model.put("desc",filteredTodos.get(0).getCatdesc());
//        return "MainPage";//返回调取View:MainPage.jsp
//    }
    // 次方的MAP根URL （'/')，即在主页调入之后调取此方法
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String layoutMainPage(ModelMap model) throws SQLException, ClassNotFoundException {
        dataBase = new DAOService(connect.connect());
        //model 相当于传参用的nsdictionary（是arraylist）
        //model addAttribute 放入参数
        //model 传过去的参数被${"id"}可以调用
        //model 用来传参，requestParam用来取参
        model.addAttribute("todos", dataBase.selectAllCustomersTable());//向html传参，key/value
        List<CustomerClass> filteredTodos = new ArrayList<CustomerClass>();
        filteredTodos = (List) model.get("todos");
        //model 传过去的参数被${key}可以调用
        model.put("cutno",filteredTodos.get(0).getCustno());
        model.put("custname",filteredTodos.get(0).getCustname());
        model.put("cdep",filteredTodos.get(0).getCdep());
        model.put("nyears",filteredTodos.get(0).getNyears());
        model.put("savtype",filteredTodos.get(0).getSavtype());
        //model.addAttribute("country", putsavingTypesInModel());
            return "MainPage";//返回调取View:MainPage.jsp
    }
    @ModelAttribute("savingType")//可直接向model里传参数
    public Map<String, String> putSavingTypesInModel() {
        Map<String, String> savingTypes = new HashMap<String, String>();
        savingTypes.put("Savings-Deluxe", "Savings-Deluxe");
        savingTypes.put("Savings-Regular", "Savings-Regular");
        return savingTypes;
    }
//    @RequestMapping(value = "/",method = RequestMethod.POST)
//    public String addBtnClickedInMain(ModelMap model,
//                                      @RequestParam String custno,
//                                      @RequestParam String custname,
//                                      @RequestParam Double cdep,
//                                      @RequestParam Integer nyears,
//                                      @RequestParam String savingType) throws SQLException, ClassNotFoundException{
//        dataBase = new DAOService(connect.connect());
//
//        model.addAttribute("savingTypes", savingType);
//
//        if (custno.length()>=1 && dataBase.selectOneCategoryByKey(custno)==null){
//            CustomerClass cc =new CustomerClass(custno, custname);
//            dataBase.add(cc);
//        }
//        return "redirect:/";
//    }
    //如果是get说明载入
    @RequestMapping(value = "/"+ ADD_TODO, method = RequestMethod.GET)
    public String jumpToAddPage(){
        return "AddPage";//返回调取View:AddPage.jsp
    }
    //如果是POST说明是在本页面提交
    @RequestMapping(value = "/" + ADD_TODO, method = RequestMethod.POST)
    public String addOneThenReturnMainPage(ModelMap model,
                                           @RequestParam String custno,
                                           @RequestParam String custname,
                                           @RequestParam Double cdep,
                                           @RequestParam Integer nyears,
                                           @RequestParam String savingType) throws SQLException, ClassNotFoundException {
        if (!(dataBase.selectOneCategoryByKey(custno)==null)){
            model.put("errorMessage","Record Existing");
            return "redirect:/";
        }
        CustomerClass cc =new CustomerClass(custno,custname,cdep,nyears,savingType);
        dataBase.add(cc);
        model.clear();
        return "redirect:/";//返回调取View:MainPage.jsp
    }
    @RequestMapping(value = "/" + UPDATE_TODO, method = RequestMethod.GET)
    public String jumpToEditPage(ModelMap model, @RequestParam(defaultValue = "") String custno) throws SQLException, ClassNotFoundException {
        model.get("custno");
        CustomerClass cc = dataBase.selectOneCategoryByKey(custno);
        model.put("custno",cc.getCustno());
        model.put("custname", cc.getCustname());
        model.put("cdep", cc.getCdep());
        model.put("nyears", cc.getNyears());
        //model.put("savingType", cc.getSavtype());
        return "EditPage";//返回调取View:EditPage.jsp
    }
    @RequestMapping(value = "/" + UPDATE_TODO, method = RequestMethod.POST)
    public String editedAndGoBack(ModelMap model,
                                  @RequestParam String custno,
                                  @RequestParam String custname,
                                  @RequestParam Double cdep,
                                  @RequestParam Integer nyears,
                                  @RequestParam String savingType) throws SQLException, ClassNotFoundException {
        //String iid = (String) model.get("id");//这个可以从session里调用id
        String iid = custno;
        CustomerClass cc = new CustomerClass(iid,custname,cdep,nyears,savingType);
        dataBase.edit(cc,iid);
        return "redirect:/";//返回到'/'根目录主页
    }
    @RequestMapping(value = "/" + DELETE_TODO, method = RequestMethod.GET)
    public String deleteTodo(ModelMap model, @RequestParam String custno) throws SQLException, ClassNotFoundException {
        dataBase.delete(custno);
        model.clear();
        return "redirect:/";//返回到'/'根目录主页
    }
    @RequestMapping(value = "/" + LIST_ALL_ITEMS,method = RequestMethod.GET)
    public String jumpToAllItem(ModelMap model, @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException{
        model.put("id" , id);
        dataBase = new DAOService(connect.connect());
        String catID = (String) model.get("id");
        List<InterestClass> itemsList = new ArrayList<>();
        itemsList = null;
        if (itemsList.size()==0){
            model.put("errorMessage","There are no items for this category");
            return "redirect:/";//返回到'/'根目录主页
        }
        model.put("desc",catID);
        System.out.println(catID);
        model.addAttribute("itemsList", itemsList);
        return "ItemsPage";//返回调取View:ItemsPage.jsp
    }
    @RequestMapping(value = "/" + LIST_ALL_ITEMS,method = RequestMethod.POST)
    public String goBackFromItemsPage(ModelMap model) throws SQLException, ClassNotFoundException{
        return "redirect:/";//返回到'/'根目录主页
    }
}