package OnlineShopping.entity;


public class OrderItem {
	
    private Long id;
    private Product product;
    private int quantity;

	public OrderItem(Product product, int quantity) {
		// TODO Auto-generated constructor stub
		this.product = product; // Assign the product
        this.quantity = quantity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    
    
}
