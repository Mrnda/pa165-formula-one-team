package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.CharacteristicsValue;

public interface CharacteristicsValueService {
    CharacteristicsValue findById(long id);
    void update(CharacteristicsValue value);
    void add(CharacteristicsValue value);
    void delete(CharacteristicsValue value);
}
