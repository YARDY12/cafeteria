package com.example.cafeteriaspring.repository;

import com.example.cafeteriaspring.model.Detalle_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_PedidoRepository extends JpaRepository<Detalle_Pedido, Integer>{

}
