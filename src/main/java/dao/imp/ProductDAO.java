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
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
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
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE product.alias = ?");
		List<ProductModel> list = query(sql.toString(), new ProductMapper(), alias);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public boolean update(ProductModel model, String id) {
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
	public ProductModel findOneById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE product.id = ?");
		List<ProductModel> list = query(sql.toString(), new ProductMapper(), id);
		return list.size() != 0 ? list.get(0) : null;
	}

	@Override
	public List<ProductModel> findAllByCategoryAndStatusAndPricePromotion(String categoryAlias, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.alias = ? AND product.status = ? AND product.price_promotional != 0 AND brand.status = 'active'");
		sql.append(" ORDER BY RAND()");
		sql.append(" LIMIT 10");
		return query(sql.toString(), new ProductMapper(), categoryAlias, status);
	}

	@Override
	public List<ProductModel> findAllByCategoryIsAccessoriesAndStatusAndPricePromotion(String isAccessories,
			String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.is_accessories = ? AND product.status = ? AND product.price_promotional != 0 AND brand.status = 'active'");
		sql.append(" ORDER BY RAND()");
		sql.append(" LIMIT 10");
		return query(sql.toString(), new ProductMapper(), isAccessories, status);
	}

	@Override
	public List<ProductModel> findAllRelatedProduct(String brandId, String idProduct, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE brand.id = ? AND product.id != ? AND product.status = ?");
		sql.append(" ORDER BY RAND()");
		sql.append(" LIMIT 4");
		return query(sql.toString(), new ProductMapper(), brandId, idProduct, status);
	}


	@Override
	public List<ProductModel> findAllCustomCondition(String condition, String categoryAlias, String status, int limit, int start, Object... parameter) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id LEFT JOIN product_classify ON product.id = product_classify.product_id");
		sql.append(" WHERE category.alias = ? AND product.status = ? ");
		sql.append(condition + " LIMIT ? OFFSET ?");
		return query(sql.toString(), new ProductMapper(), categoryAlias, status, parameter, limit, start);
	}


	@Override
	public List<ProductModel> findAllByCategoryAndStatus(String categoryAlias, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.alias = ? AND product.status = ?");
		sql.append(" ORDER BY created_date DESC");
		return query(sql.toString(), new ProductMapper(), categoryAlias, status);
	}


	@Override
	public boolean delete(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM product");
		sql.append(" WHERE id = ?;");
		return excute(sql.toString(), id);
	}


	@Override
	public List<ProductModel> findAllByBrandIdAndStatus(String brandId, String status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE brand.id = ? AND product.status = ?");
		return query(sql.toString(), new ProductMapper(), brandId, status);
	}


	@Override
	public boolean updateQty(int qty, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE product");
		sql.append(" SET qty = ?");
		sql.append(" WHERE id = ?");
		return excute(sql.toString(), qty,  id);
	}


	@Override
	public int countAllCustomCondition(String condition, String categoryAlias, String status, Object... idsParameter) {
		
		StringBuilder sql = new StringBuilder();
		if(condition != "" && condition.contains("classify")) {
		sql.append("SELECT COUNT(1) AS countt FROM (SELECT COUNT(*) AS dem FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id LEFT JOIN product_classify ON product.id = product_classify.product_id");
		sql.append(" WHERE category.alias = ? AND product.status = ? ");
		sql.append(condition +" ) product GROUP BY dem");
		} else if(condition != "" && condition.contains("brand")){
			sql.append("SELECT DISTINCT COUNT(*) AS dem FROM product");
			sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id ");
			sql.append(" WHERE category.alias = ? AND product.status = ? "+ condition);
		} else {
			sql.append("SELECT DISTINCT COUNT(*) AS dem FROM product");
			sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id ");
			sql.append(" WHERE category.alias = ? AND product.status = ? ");
		}
		return queryCount(sql.toString(), categoryAlias, status, idsParameter);
	}


	@Override
	public List<ProductModel> findAllByKeyword(String condition, Object... keysParameter) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.*, brand.name AS brand_name, brand.alias AS brand_alias, category.alias AS category_alias, category.name AS category_name FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE " + condition);
		return query(sql.toString(), new ProductMapper(), keysParameter);
	}


	@Override
	public boolean updateRated(double rated, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE product");
		sql.append(" SET rated = ?");
		sql.append(" WHERE id = ?");
		return excute(sql.toString(), rated,  id);
	}


	@Override
	public boolean updateIsNew(String isNew, String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE product");
		sql.append(" SET is_new = ?");
		sql.append(" WHERE id = ?");
		return excute(sql.toString(), isNew,  id);
	}


	@Override
	public List<ProductModel> findAllByCategoryAndIsNewAndStatusAndLimit(String categoryAlias, String isNew,
			String status, int limit) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.* FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.alias = ? AND product.status = ? AND brand.status = 'active' AND is_new = ?");
		sql.append(" ORDER BY created_date DESC");
		sql.append(" LIMIT ?");
		return query(sql.toString(), new ProductMapper(), categoryAlias, status, isNew, limit);
	}


	@Override
	public List<ProductModel> findAllByCategoryIsAccessoriesAndIsNewAndStatusAndLimit(String is_accessories,
			String isNew, String status, int limit) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT product.* FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.is_accessories = ? AND product.status = ? AND brand.status = 'active' AND is_new = ?");
		sql.append(" ORDER BY created_date DESC");
		sql.append(" LIMIT ?");
		return query(sql.toString(), new ProductMapper(), is_accessories, status, isNew, limit);
	}


	@Override
	public int countAll() {
		String sql = "SELECT COUNT(*) FROM product";
		return queryCount(sql);
	}


	@Override
	public int countAllByCategory(String category) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM product");
		sql.append(" JOIN brand ON product.brand_id = brand.id JOIN category ON category.id = brand.category_id");
		sql.append(" WHERE category.alias = ? ");
		return queryCount(sql.toString(), category);
	}

}
