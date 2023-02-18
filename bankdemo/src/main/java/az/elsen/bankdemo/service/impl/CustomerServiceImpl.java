package az.elsen.bankdemo.service.impl;

import az.elsen.bankdemo.Enums.EnumAvailableStatus;
import az.elsen.bankdemo.dto.request.ReqCustomer;
import az.elsen.bankdemo.dto.response.RespCustomer;
import az.elsen.bankdemo.dto.response.RespStatus;
import az.elsen.bankdemo.dto.response.Response;
import az.elsen.bankdemo.entity.Customer;
import az.elsen.bankdemo.exception.BankException;
import az.elsen.bankdemo.exception.ExceptionConstants;
import az.elsen.bankdemo.repository.CustomerRepository;
import az.elsen.bankdemo.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service //arxada service bean yaradir
//@Component //bunu istifade etdikde Service yaxud Repository annotationun isletmeye ehtiyac olmur
@RequiredArgsConstructor //private final deyiseni cagirir. Eynile @Autowired annotation-un gorduyu isi yerine yetirir
public class CustomerServiceImpl implements CustomerService {
    /*
        @Autowired // -->Uyğun olaraq üzərində @Companent @Repository @Service annotation-lari olan class-in bean-in yaradır və mənimsədir.
        private CustomerRepository customerRepository;
    */
    private final CustomerRepository customerRepository;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        try {
            List<RespCustomer> responseCustomerList = new ArrayList<>();
            List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (customerList.isEmpty()) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            for (Customer customer : customerList) {
                RespCustomer respCustomer = convert(customer);
                responseCustomerList.add(respCustomer);
            }
            response.setT(responseCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public Response<RespCustomer> getCustomerById(Long customerId) {
        Response<RespCustomer> response = new Response<>();
        try {
            if (customerId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
           /* RespCustomer respCustomer = new RespCustomer();
            respCustomer.setId(customer.getId());
            respCustomer.setName(customer.getName());
            respCustomer.setSurname(customer.getSurname());
            respCustomer.setDob(customer.getDob());
            respCustomer.setAddress(customer.getAddress());
            respCustomer.setPhone(customer.getPhone());
            respCustomer.setCif(customer.getCif());
            respCustomer.setPin(customer.getPin());
            respCustomer.setSeria(customer.getSeria());
            respCustomer.setDataDate(customer.getDataDate());
            respCustomer.setActive(customer.getActive());*/
            RespCustomer respCustomer = convert(customer);
            response.setT(respCustomer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public Response addCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (name == null || surname == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data");
            }
            Customer customer = Customer.builder().
                    name(name).
                    surname(surname).
                    dob(reqCustomer.getDob()).
                    address(reqCustomer.getAddress()).
                    phone(reqCustomer.getPhone()).
                    cif(reqCustomer.getCif()).
                    pin(reqCustomer.getPin()).
                    seria(reqCustomer.getSeria()).
                    build();
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }

        return response;
    }

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
            Long id = reqCustomer.getCustomerId();
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (id == null || name == null || surname == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer Not Found");
            }
            customer.setId(id);
            customer.setName(name);
            customer.setSurname(surname);
            customer.setDob(reqCustomer.getDob());
            customer.setAddress(reqCustomer.getAddress());
            customer.setPhone(reqCustomer.getPhone());
            customer.setCif(reqCustomer.getCif());
            customer.setPin(reqCustomer.getPin());
            customer.setSeria(reqCustomer.getSeria());

            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    @Override
    public Response deleteCustomer(Long customerId) {
        Response response = new Response();
        try {
            if (customerId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer Not Found");
            }
            customer.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
        }
        return response;
    }

    private RespCustomer convert(Customer customer) {
        RespCustomer respCustomer = RespCustomer.builder().
                id(customer.getId()).
                name(customer.getName()).
                surname(customer.getSurname()).
                dob(customer.getDob() != null ? dateFormat.format(customer.getDob()) : null).
                address(customer.getAddress()).
                phone(customer.getPhone()).
                cif(customer.getCif()).
                pin(customer.getPin()).
                seria(customer.getSeria()).
                dataDate(customer.getDataDate() != null ? dateFormat.format(customer.getDataDate()) : null).
                active(customer.getActive()).
                build();
        return respCustomer;
    }
}
