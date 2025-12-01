package delivery_api.dto.response;

import com.deliverytech.delivery_api.dto.request.ItemPedidoRequestDTO;
import com.deliverytech.delivery_api.entity.Cliente;
import com.deliverytech.delivery_api.entity.Restaurante;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDTO {
    private Long id;
    private String numeroPedido;
    private LocalDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;
    private String observacoes;
    private Cliente cliente;
    private Restaurante restaurante;
    private String enderecoEntrega;
    private BigDecimal taxaEntrega;
    List<ItemPedidoRequestDTO> itens;

}
