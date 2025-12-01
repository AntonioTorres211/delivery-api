package delivery_api.services;

import com.deliverytech.delivery_api.dto.request.ClienteRequestDTO;
import com.deliverytech.delivery_api.dto.response.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    ClienteResponseDTO cadastrar(ClienteRequestDTO dto);

    ClienteResponseDTO buscarPorId(Long id);

    ClienteResponseDTO buscarPorEmail(String email);

    ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto);

    ClienteResponseDTO ativarDesativarCliente(Long id);

    List<ClienteResponseDTO> listarAtivos();

    List<ClienteResponseDTO> buscarPorNome(String nome);
    
}