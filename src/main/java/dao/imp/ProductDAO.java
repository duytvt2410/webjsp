package dao.imp;

import java.util.List;

import dao.IProductDAO;
import mapper.ProductMapper;
import model.ProductModel;

public class ProductDAO extends AbstactDAO<ProductModel> implements IProductDAO {
	
private static ProductDAO dao = null;
	
	public static ProductDAO getInstance() {
		if(dao == null) {
			dao = new ProductDAO();
		}
		return dao;
	}


	@Override
	public List<ProductModel> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public boolean insert(ProductModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO product (id, name, alias, brand_id, price, price_promotional, qty, promotion_information, description, specifications, status,created_date, created_by, updated_date, updated_by)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return excute(sql.toString(), model.getId(), model.getName(), model.getAlias(), model.getBrandId(),
				model.getPrice(), model.getPricePromotional(), model.getQty(), model.getPromotionInformation(),
				model.getDescription(), model.getSpecifications(),
				model.getStatus(), model.getCreateDate(), model.getCreateBy(), model.getUpdateDate(),
				model.getUpdateBy());
	}

	@Override
	public ProductModel findOneByAlias(String alias) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE product.alias = ?");
		List<ProductModel> list = query(sql.toString(), new ProductMapper(), alias);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean update(ProductModel model, Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE product");
		sql.append(" SET name = ?, alias = ?, status = ?, brand_id = ?, price = ?, price_promotional = ?, qty = ?, promotion_information = ?, description = ?, specifications = ?, updated_date = ?, updated_by = ?");
		sql.append(" WHERE id = ?");
		return excute(sql.toString(), model.getName(), model.getAlias(), model.getStatus(), model.getBrandId(),
				model.getPrice(), model.getPricePromotional(), model.getQty(), model.getPromotionInformation(),
				model.getDescription(), model.getSpecifications(), model.getUpdateDate(),
				model.getUpdateBy(), id);
	}

	@Override
	public ProductModel findOneById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE product.id = ?");
		List<ProductModel> list = query(sql.toString(), new ProductMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public List<ProductModel> findAllByIsAccessories(String isAccessories) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category WHERE is_accessories = ?");
		return query(sql.toString(), new ProductMapper(), isAccessories);
	}

	@Override
	public List<ProductModel> findAllByIsAccessoriesAndStatus(String isAccessories, String status) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category WHERE is_accessories = ? AND status = ?");
		return query(sql.toString(), new ProductMapper(), isAccessories, status);
	}

	@Override
	public List<ProductModel> findAllByStatus(String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM category WHERE status = ?");
		return query(sql.toString(), new ProductMapper(), status);
	}

	@Override
	public List<ProductModel> findAllByCategoryAndStatusAndOrderByCreateDate(String categoryAlias, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.alias = ? AND product.status = ?");
		sql.append(" ORDER BY created_date DESC");
		sql.append(" LIMIT 10");
		return query(sql.toString(), new ProductMapper(), categoryAlias, status);
	}

	@Override
	public List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndOrderByCreateDate(String isAccessories, String status) {
			StringBuilder sql = new StringBuilder();
	sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
	sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
	sql.append(" WHERE category.is_accessories = ? AND product.status = ?");
	sql.append(" ORDER BY created_date DESC");
	sql.append(" LIMIT 10");
	return query(sql.toString(), new ProductMapper(), isAccessories, status);
	}

	@Override
	public List<ProductModel> findAllByCategoryAndStatusAndPricePromotion(String categoryAlias, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.alias = ? AND product.status = ? AND product.price_promotional != 0");
		sql.append(" ORDER BY RAND()");
		sql.append(" LIMIT 10");
		return query(sql.toString(), new ProductMapper(), categoryAlias, status);
	}

	@Override
	public List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndPricePromotion(String isAccessories,
			String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.is_accessories = ? AND product.status = ? AND product.price_promotional != 0");
		sql.append(" ORDER BY RAND()");
		sql.append(" LIMIT 10");
		return query(sql.toString(), new ProductMapper(), isAccessories, status);
	}

	@Override
	public List<ProductModel> findAllRelatedProduct(Long brandId, Long idProduct, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE brand.id = ? AND product.id != ? AND product.status = ?");
		sql.append(" ORDER BY RAND()");
		sql.append(" LIMIT 4");
		return query(sql.toString(), new ProductMapper(), brandId, idProduct, status);
	}


	@Override
	public List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status, Object... parameter) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id LEFT JOIN product_classify ON product.id = product_classify.product_id");
		sql.append(" WHERE category.alias = ? AND product.status = ? ");
		sql.append(condition);
		return query(sql.toString(), new ProductMapper(), categoryAlias, status, parameter);
	}


	@Override
	public List<ProductModel> findAllByCategoryAndStatus(String categoryAlias, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.alias = ? AND product.status = ?");
		sql.append(" ORDER BY created_date DESC");
		return query(sql.toString(), new ProductMapper(), categoryAlias, status);
	}

}
