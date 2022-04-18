package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {

	// field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;


	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	// ctor
	public PurchaseController() {
		System.out.println(this.getClass());
	}


	// method
	@RequestMapping(value="/addPurchase", method=RequestMethod.GET)
	public ModelAndView addPurchaseView(@RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu) throws Exception {

		System.out.println("/purchase/addPurchase : GET");
		
		Product product = new Product();
		product = productService.getProduct(prodNo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		modelAndView.addObject("product", product);

		System.out.println(product); // for debugging
		
		return modelAndView;
	}

	@RequestMapping(value="/addPurchase", method=RequestMethod.POST)
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase, @ModelAttribute("product") Product product, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
	
		System.out.println("/purchase/addPurchase : POST");
		
		product = productService.getProduct(product.getProdNo());
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer((User) session.getAttribute("user"));
		
		purchaseService.addPurchase(purchase);
		
		redirectAttributes.addFlashAttribute("purchase", purchase);
		
		System.out.println(purchase);

		return "redirect:/addPurchaseSuccess";
	}
	
	
	@RequestMapping(value="/addPurchaseSuccess", method=RequestMethod.GET)
	public ModelAndView addPurchaseSuccess() throws Exception {
		
		System.out.println("/addPurchaseSuccess");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		
		return modelAndView;
		
	}


	@RequestMapping(value="/getPurchase")
	public ModelAndView getPurchase(@RequestParam("tranNo") int tranNo) throws Exception {

		System.out.println("/getPurchase");

		Purchase purchase = purchaseService.getPurchase(tranNo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/getPurchase.jsp");
		modelAndView.addObject("purchase", purchase);
		
		System.out.println(purchase);
 
		return modelAndView;

	}


	@RequestMapping("/listPurchase")
	public ModelAndView listPurchase( @ModelAttribute("search") Search search, @RequestParam("menu") String menu, HttpSession session) throws Exception {

		System.out.println("/listPurchase");

		User user = (User) session.getAttribute("user");

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = purchaseService.getPurchaseList(search, user.getUserId());
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/listPurchase.jsp");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		modelAndView.addObject("menu", menu);

		return modelAndView;		
	}


	@RequestMapping("/listSale")
	public ModelAndView listSale(@ModelAttribute("search") Search search, HttpSession session) throws Exception {

		//@RequestParam("menu") String menu, //TODO listsale menu left.jsp에서 해결
		System.out.println("/listSale");

		String userId = ((User) session.getAttribute("user")).getUserId();
		
		System.out.println(userId);

		if(search.getCurrentPage() == 0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getUserSaleList(search, userId);
		
		if(userId.equals("admin")) {
			map = purchaseService.getSaleList(search);
		} 
		
		System.out.println(map);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/listSale.jsp");
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		//modelAndView.addObject("menu", menu);
		
		return modelAndView;

	}

	
	@RequestMapping(value="/updatePurchase", method=RequestMethod.GET)
	public ModelAndView updatePurchaseView( @RequestParam("tranNo") int tranNo ) throws Exception {
		
		System.out.println("/updatePurchase : GET");
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/updatePurchaseView.jsp");
		modelAndView.addObject("purchase", purchase);
		
		return modelAndView;
		
	}

	
	@RequestMapping(value="/updatePurchase", method=RequestMethod.POST)
	public ModelAndView updatePurchase( @ModelAttribute("purchase") Purchase purchase ) throws Exception {
		
		System.out.println("/updatePurchase : POST");
		
		purchaseService.updatePurchase(purchase);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/getPurchase");
		
		return modelAndView;
	}

	
	@RequestMapping("/updateTranCodeByProd")
	public ModelAndView updateTranCodeByProd(@RequestParam("tranNo") int tranNo, @RequestParam("tranCode") String tranCode) throws Exception {
		
		System.out.println("/updateTranCodeByProd.do");
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		purchase.setTranCode(tranCode);
		
		purchaseService.updateTranCode(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/listSale.do");
	
		return modelAndView;
	}

}
