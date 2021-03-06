package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemStockDO;
import org.apache.ibatis.annotations.Param;

public interface ItemStockDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Sun Jan 27 14:38:40 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Sun Jan 27 14:38:40 CST 2019
     */
    int insert(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Sun Jan 27 14:38:40 CST 2019
     */
    int insertSelective(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Sun Jan 27 14:38:40 CST 2019
     */
    ItemStockDO selectByPrimaryKey(Integer id);

    /**
     * this method use to find the item by itemId
     * @param itemId item id
     * @return itemStockDataObject
     */
    ItemStockDO selectByItemId(Integer itemId);


    /**
     * this method use to decrease the stock when the user ordering
     * @param itemId item id
     * @param amount the number of ordered item
     * @return  them number of remain item
     */
    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount") Integer amount);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Sun Jan 27 14:38:40 CST 2019
     */
    int updateByPrimaryKeySelective(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Sun Jan 27 14:38:40 CST 2019
     */
    int updateByPrimaryKey(ItemStockDO record);
}