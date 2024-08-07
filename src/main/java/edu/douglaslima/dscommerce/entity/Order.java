package edu.douglaslima.dscommerce.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant moment;
	private OrderStatus status;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> orderItems = new HashSet<>();
	
	public Order() {}

	public Order(Long id, Instant moment, OrderStatus status, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public User getClient() {
		return client;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	
	public List<Product> getProducts() {
		return orderItems.stream()
				.map(OrderItem::getProduct)
				.toList();
	}
	
}
