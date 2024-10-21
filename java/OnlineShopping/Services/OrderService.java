package OnlineShopping.Services;

import java.util.List;

import OnlineShopping.dao.OrderDAO;
import OnlineShopping.entity.Order;
import OnlineShopping.entity.OrderItem;
import OnlineShopping.entity.User;

public class OrderService {
    public Order createOrder(User loggedInUser, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUser(loggedInUser);
        order.setOrderItems(orderItems);
        order.setTotalAmount(calculateTotal(orderItems));
        // Save the order using OrderDAO
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.saveOrder(order);
        return order;
    }

    private double calculateTotal(List<OrderItem> orderItems) {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getProduct().getPrice() * item.getQuantity(); // Assuming Product has a getPrice method
        }
        return total;
    }

	public Order createOrder1(String loggedInUser, List<OrderItem> orderItems) {
		// TODO Auto-generated method stub
		return null;
	}
}
