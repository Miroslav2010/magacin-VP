package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Category;
import mk.ukim.finki.wp.magacin.models.Manufacturer;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp.magacin.repository.ManufacturerRepository;
import mk.ukim.finki.wp.magacin.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> listAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findbyId(Long id) {
        return this.manufacturerRepository.findById(id).orElseThrow(InvalidManufacturerIdException::new);
    }

    @Override
    public Manufacturer create(String name) {
        return this.manufacturerRepository.save(new Manufacturer(name));
    }

    @Override
    public Manufacturer update(Long id, String name) {
        Manufacturer manufacturer = this.findbyId(id);
        manufacturer.setName(name);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer delete(Long id) {
        Manufacturer manufacturer = this.findbyId(id);
        this.manufacturerRepository.delete(manufacturer);
        return manufacturer;
    }
}
