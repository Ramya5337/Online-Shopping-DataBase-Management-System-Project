package OnlineShopping.Services;

import java.util.List;

import OnlineShopping.dao.ProductDao;
import OnlineShopping.entity.Product;

public class ProductService {
	ProductDao productDao = new ProductDao();

    public void addProduct(String name, String description, double price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productDao.save(product);
    }

    public Product getProductById(Long id) {
        return productDao.getById(id);
    }

    public void updateProduct(Long id, String name, String description, double price) {
        Product product = productDao.getById(id);
        if (product != null) {
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            productDao.update(product);
        }
    }

    public void deleteProduct(Long id) {
        productDao.delete(id);
    }

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}

	
}


