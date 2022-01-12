package com.moshin.loan.service.tipo_prestamo;

import java.util.ArrayList;
import java.util.List;

import com.moshin.loan.entity.TipoPrestamo;
import com.moshin.loan.repository.TipoPrestamoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoPrestamoServiceImpl implements TipoPrestamoService {

    @Autowired
    TipoPrestamoRepository tipoPrestamoRepository;

    @Override
    @Transactional
    public TipoPrestamo save(TipoPrestamo tipoPrestamo) {
        return tipoPrestamoRepository.save(tipoPrestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoPrestamo> getTipoByRuc(String cRuc) {
        List<TipoPrestamo> listTipo = new ArrayList<>();
        tipoPrestamoRepository.findAll().forEach(tipoPrestamo->{
            if(tipoPrestamo.getEmpresa().getCRUC().equals(cRuc)){
                listTipo.add(tipoPrestamo);
            }
        });
        return listTipo;
    }

    @Override
    @Transactional
    public TipoPrestamo logicalDelete(Long id) {
        TipoPrestamo tipoPrestamo = tipoPrestamoRepository.getById(id);
        tipoPrestamo.setCEstado("0");
        return tipoPrestamoRepository.save(tipoPrestamo);
    }
    
}
