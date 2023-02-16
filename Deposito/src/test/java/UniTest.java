import DepositoDM.Modelo;
import DepositoDM.Producto;
import DepositoDM.Proveedor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniTest {

    @Test
    void test1(){
        var p = Modelo.viewProveedor();
        Proveedor proveedorPrueba = new Proveedor("6EF9","Franco");
        Proveedor objeto = p.get(4);
        assertEquals(objeto.getId(),proveedorPrueba.getId());
    }

    @Test
    void test2(){
        var pr = Modelo.verProductos();
        Producto producto = new Producto("216A","SmartTV",150,"LG","3C34");
        Producto ob = pr.get(0);
        assertEquals(ob.getIdProducto(),producto.getIdProducto());
    }
}
