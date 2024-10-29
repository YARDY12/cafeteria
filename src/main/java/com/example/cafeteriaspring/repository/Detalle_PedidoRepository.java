package com.example.cafeteriaspring.repository;

import com.example.cafeteriaspring.model.Detalle_Pedido;
import com.example.cafeteriaspring.model.Pedido;
import com.example.cafeteriaspring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Detalle_PedidoRepository extends JpaRepository<Detalle_Pedido, Integer>{

    List<Detalle_Pedido> findByPedido(Pedido pedido);
    List<Detalle_Pedido> findByProducto(Producto producto);

}
