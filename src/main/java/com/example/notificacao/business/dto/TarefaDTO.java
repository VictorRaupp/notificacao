package com.example.notificacao.business.dto
;



import com.example.notificacao.infrastructure.enums.StatusTarefaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTO {

    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private StatusTarefaEnum statusTaferaEnum;
}
