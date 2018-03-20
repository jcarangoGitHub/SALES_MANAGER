package persistence;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import models.ItemEntity;

import java.util.ArrayList;
import java.util.List;


public class ItemPersistence {
    //@PersistenceContext(name = "defaultPersistenceUnit") EntityManager entityManager;

    public List<ItemEntity> getAllItems() {
        SqlQuery sqlQuery = Ebean.createSqlQuery("select * from item where quantity > 0 order by name");


        List<SqlRow> items = sqlQuery.findList();
        List<ItemEntity> list = new ArrayList<ItemEntity>();
        ItemEntity item;
        for (SqlRow it : items) {
            item = new ItemEntity();
            item.setId(it.getInteger("id"));
            item.setName(it.getString("name"));
            item.setDescription(it.getString("description"));
            item.setQuantity(it.getInteger("quantity"));
            list.add(item);
        }

        return list;
    }

    public ItemEntity getItemById(int id) {
        return  Ebean.find(ItemEntity.class)
                .where()
                .eq("id", id).findUnique();
    }

    public void updateQauntity(ItemEntity item, Integer newValue) {
        item.setQuantity(newValue);
        item.save();
    }
}
