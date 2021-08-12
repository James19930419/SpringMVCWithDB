import com.example.SpringMVC2.CategoryClass;
import com.example.SpringMVC2.DAOService;
import com.example.SpringMVC2.PDOService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class DAO_UnitTest {
    @InjectMocks
    private DAOService daoService;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet resultSet;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private PDOService connection1;

    CategoryClass category;

    @BeforeEach
    public void Setup() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection1.connect()).thenReturn(connection);
        category=new CategoryClass("101","food");
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        String queryStr = "INSERT INTO category VALUES ( ?, ? )";
        when(connection.prepareStatement(queryStr,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)).thenReturn(preparedStatement);
        daoService.add(category);
        verify(preparedStatement).executeUpdate();

    }

    @Test
    void search() throws SQLException, ClassNotFoundException {
        String quer1 = "Select * from category where catcode = ?";
        when(connection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("catcode")).thenReturn("101");
        when(resultSet.getString("catdesc")).thenReturn("food");
        when(resultSet.first()).thenReturn(true);
        CategoryClass categ=daoService.selectOneCategoryByKey("101");
        assertEquals("101",categ.getCatcode());

    }
    @Test
    void SearchIfNoRecord() throws SQLException, ClassNotFoundException {
        String quer1 = "Select * from category where catcode = ?";
        when(connection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("catcode")).thenReturn("norecord");
        when(resultSet.getString("catdesc")).thenReturn("norecord");


        CategoryClass categ=daoService.selectOneCategoryByKey("norecord");
        Assertions.assertNull(categ);

    }

    @Test
    void edit() throws SQLException, ClassNotFoundException {
//        when(resultSet.next()).thenReturn(true).thenReturn(false);
//        when(resultSet.getString("catcode")).thenReturn("101");
//        when(resultSet.getString("catdesc")).thenReturn("food");
//        when(resultSet.first()).thenReturn(true);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//
//        CategoryClass categ=daoService.search("101");
        category=new CategoryClass("101","Food&Bev");
        when(connection.prepareStatement("Update category set catcode=?, catdesc=? where catcode = ?",ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)).thenReturn(preparedStatement);
        CategoryClass cat1=daoService.edit(category,"101");
        assertEquals("Food&Bev",cat1.getCatdesc());
    }

    @Test
    void delete() throws SQLException, ClassNotFoundException {
        String quer1 = "Delete from Category where catcode = ?";
        when(connection.prepareStatement(quer1,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)).thenReturn(preparedStatement);
        daoService.delete("101");
        verify(preparedStatement).executeUpdate();

    }

    @Test
    void display() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("catcode")).thenReturn("101");
        when(resultSet.getString("catdesc")).thenReturn("food");
        when(resultSet.first()).thenReturn(true);

        doReturn(resultSet).when(preparedStatement).executeQuery();
        daoService.selectAllCategoryTable();
        verify(preparedStatement).executeQuery();

    }
}

