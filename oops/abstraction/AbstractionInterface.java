package oops.abstraction;
/*
Abstraction: it can be achieved using abstract class or interface
 */

interface Pay{
   void pay();
}
class UpiPay implements Pay {
    @Override
    public void pay() {
        System.out.println("Payment using UPI");
    }
}
class CreditCardPay implements Pay{
    @Override
    public void pay() {
        System.out.println("Payment using Credit Card");
    }
}
public class AbstractionInterface implements Pay {
    @Override
    public void pay() {
        System.out.println("doing payment...");
    }

    public static void main(String[] args) {
        Pay payment  = new AbstractionInterface();
        payment.pay();
        Pay upiPayment  = new UpiPay();
        upiPayment.pay();
        Pay creditCardPayment = new CreditCardPay();
        creditCardPayment.pay();
    }

}
