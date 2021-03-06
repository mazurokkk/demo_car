package oleh.mazurok.demo_car.controller;

//import oleh.mazurok.demo_car.service.OrderService;

import oleh.mazurok.demo_car.dto.request.OrderedRequest;
import oleh.mazurok.demo_car.dto.response.OrderedResponse;
import oleh.mazurok.demo_car.entity.Ordered;
import oleh.mazurok.demo_car.security.JwtTokenTool;
import oleh.mazurok.demo_car.service.OrderService;
import oleh.mazurok.demo_car.service.UserService;
import oleh.mazurok.beans.factory.annotation.Autowired;
import oleh.mazurok.web.bind.annotation.GetMapping;
import oleh.mazurok.web.bind.annotation.PathVariable;
import oleh.mazurok.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/ordered")
public class OrderedController {
    @Autowired
    private JwtTokenTool jwtTokenTool;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/make_an_order/{userToken}/{productId}")///{id}")
    public ModelAndView create(@PathVariable("userToken") String userToken,
                               @PathVariable("productId") Long productId,
                               OrderedRequest orderedRequest) {
        System.err.println("userToken = " + userToken);
        System.err.println("productId" + productId);
        Long userId = userService.findUserIdByToken(userToken);
        Ordered ordered = orderService.create(orderedRequest, userId, productId);
        System.out.println(ordered);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ordered", ordered);
        modelAndView.setViewName("html/orderMade");
        return modelAndView;
    }

    @GetMapping("/allUserOrderers/{userToken}")
    private ModelAndView allUserOrderers(@PathVariable("userToken") String userToken) {
        Long userId = userService.findByUsername(jwtTokenTool.getUsername(userToken)).getId();
        List<OrderedResponse> allUserOrderers = orderService.findAllUserOrderers(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userToken", userToken);
        modelAndView.addObject("allUserOrderers", allUserOrderers);
        modelAndView.setViewName("html/userOrderers");
        return modelAndView;
    }
}
