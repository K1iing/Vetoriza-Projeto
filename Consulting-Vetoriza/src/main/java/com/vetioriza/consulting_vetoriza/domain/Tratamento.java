package com.vetioriza.consulting_vetoriza.domain;

import com.vetioriza.consulting_vetoriza.dto.consulting.ConsultDTO;
import com.vetioriza.consulting_vetoriza.enums.TipoEvento;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "COR_TRATAMENTO")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tratamentoId;

    @Column(name = "titulo_tratamento")
    private String tituloTratamento;

    @Column(name = "descricao_tratamento")
    private String descriptionTratamento;

    @Column(name = "tipo_evento")
    private TipoEvento tipoEvento;

    @Column(name = "horario_tratamento")
    private LocalDateTime horarioTratamento;

    @Column(name = "medico_id")
    private Long medicalId;

    @Column(name = "paciente_id")
    private Long patientId;

    @Column(name = "tratamento_finalizado")
    @Nullable
    private Boolean tratamentoFinalizado;


    public Tratamento(Long patientId, Long medicalId, LocalDateTime horarioTratamento, TipoEvento tipoEvento, String tituloTratamento, String descriptionTratamento) {
        this.patientId = patientId;
        this.medicalId = medicalId;
        this.horarioTratamento = horarioTratamento;
        this.tipoEvento = tipoEvento;
        this.tituloTratamento = tituloTratamento;
        this.descriptionTratamento = descriptionTratamento;
    }

    public Tratamento(Long tratamentoId, String tituloTratamento, String descriptionTratamento, TipoEvento tipoEvento, LocalDateTime horarioTratamento, Long medicalId, Long patientId, boolean tratamentoFinalizado) {
        this.tratamentoId = tratamentoId;
        this.tituloTratamento = tituloTratamento;
        this.descriptionTratamento = descriptionTratamento;
        this.tipoEvento = tipoEvento;
        this.horarioTratamento = horarioTratamento;
        this.medicalId = medicalId;
        this.patientId = patientId;
        this.tratamentoFinalizado = tratamentoFinalizado;
    }

    public Tratamento(ConsultDTO data) {
        this.patientId = data.patientId();
        this.medicalId = data.medicalId();
        this.horarioTratamento = data.horarioTratamento();
        this.tipoEvento = data.tipoEvento();
        this.tituloTratamento = data.tituloTratamento();
        this.descriptionTratamento = data.descriptionConsult();
    }

    public Tratamento() {
    }

    public boolean isTratamentoFinalizado() {
        return tratamentoFinalizado != null ? tratamentoFinalizado : false;
    }

    public void setTratamentoFinalizado(boolean tratamentoFinalizado) {
        this.tratamentoFinalizado = tratamentoFinalizado;
    }

    public Long getTratamentoId() {
        return tratamentoId;
    }

    public void setTratamentoId(Long tratamentoId) {
        this.tratamentoId = tratamentoId;
    }

    public String getDescriptionTratamento() {
        return descriptionTratamento;
    }

    public void setDescriptionTratamento(String descriptionTratamento) {
        this.descriptionTratamento = descriptionTratamento;
    }

    public String getTituloTratamento() {
        return tituloTratamento;
    }

    public void setTituloTratamento(String tituloTratamento) {
        this.tituloTratamento = tituloTratamento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public LocalDateTime getHorarioTratamento() {
        return horarioTratamento;
    }

    public void setHorarioTratamento(LocalDateTime horarioTratamento) {
        this.horarioTratamento = horarioTratamento;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(Long medicalId) {
        this.medicalId = medicalId;
    }
}
