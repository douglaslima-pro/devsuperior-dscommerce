package edu.douglaslima.dscommerce.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK {
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;
	
	public OrderItemPK() {}

	public OrderItemPK(Product product, Order order) {
		super();
		this.product = product;
		this.order = order;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(product, order);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof OrderItemPK)) {
			return false;
		}
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(product, other.product) && Objects.equals(order, other.order);
	}

	public Product getProduct() {
		return product;
	}

	public Order getOrder() {
		return order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
