package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Pedido;


public class PedidoDAO {

    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "";


        return pedidos;
    }

    public boolean addPedido(Pedido p) {




        return false;
    }

    public boolean actualizarPedido(int id_pedido, int cantidad, LocalDate fecha) {



        return false;
    }

    public boolean cancelarPedido(String nombre) {


        return false;
    }
}
