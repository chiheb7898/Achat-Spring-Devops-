/*
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import tn.esprit.rh.achat.entities.Stock;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class StockServiceImplTest {

@Autowired
IStockService ss;
@Test
@Order(1)
public void testRetrieveAllUsers() {
List<Stock> listStocks = ss.retrieveAllStocks();
Assertions.assertEquals(0, listStocks.size());
}
}
*/