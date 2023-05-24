package cn.itcast.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static cn.itcast.hotel.constants.HotelConstants.MAPPING_TEMPLATE;

/**
 * @author psj
 * @date 2023/5/22 21:21
 * @File: HotelIndexTest.java
 * @Software: IntelliJ IDEA
 */


public class HotelIndexTest {
    private RestHighLevelClient restHighLevelClient;

    @Test
    void testInit() {
        System.out.println(restHighLevelClient);
    }

    @BeforeEach
    void setUp() {
        this.restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://服务器IP:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.restHighLevelClient.close();
    }

    @Test
    void testCreateHotelIndex() throws IOException {
        // 1.创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        // 2.请求参数(MAPPING_TEMPLATE的内容是创建索引库的DSL语句)
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        // 3.发起请求
        // indices方法的返回值是IndicesClient类型，封装了所有与索引库操作有关的方法
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    void testDeleteHotelIndex() throws IOException {
        // 1.创建Request对象
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        // 2.发送请求
        restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testExistsHotelIndex() throws IOException {
        // 1.创建Request对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        // 2.发送请求
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        // 3.输出
        System.err.println(exists ? "索引库已经存在！" : "索引库不存在！");
    }
}
