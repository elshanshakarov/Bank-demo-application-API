package az.elsen.bankdemo.controller;

import az.elsen.bankdemo.dto.request.ReqCustomer;
import az.elsen.bankdemo.dto.response.RespCustomer;
import az.elsen.bankdemo.dto.response.Response;
import az.elsen.bankdemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RestController-in @Controller-dən fərqi ondan ibarətdir ki, method birbaşa data-nı json qaytara bilir
@RequestMapping("/customer")
@RequiredArgsConstructor //final dəyişənlərin constructor-ların yaradır
public class CustomerController {
    private final CustomerService customerService;

    // @RequestMapping(value = "/GetCustomeList", method = {RequestMethod.GET,RequestMethod.POST}) //bu methodu web method etmək üçün ist olunur.Həm get həmdə postnan sorğu göndərmək istəsək belə yazırıq.
    //@GetMapping(value = "/GetCustomerList",consumes = {MediaType.APPLICATION_XML_VALUE}) //Bu formada yazaraq Data-ni XML kimi qaytara bilerik
    @GetMapping("/GetCustomerList") //Yalnız get ilə sorğu göndərməy istədikdə belə yazırıq
    public Response<List<RespCustomer>> getCustomerList() {
        return customerService.getCustomerList();
    }

    @GetMapping("/GetCustomerById") //Yalnız get ilə sorğu göndərməy istədikdə belə yazırıq
    //Eger request-e bir melumat gonderirikse Query Param kimi @RequestParam, Path Param kimi ise @PathVariable annotasion-dan ist. olunur
    public Response<RespCustomer> getCustomerById(@RequestParam Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/AddCustomer")
    //@RequestBody --> Melumatı Json formatında toplu şəkildə göndəririk ist. olunur
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.addCustomer(reqCustomer);
    }

    @PutMapping("/UpdateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PutMapping("/DeleteCustomer/{customerId}")
    public Response deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
