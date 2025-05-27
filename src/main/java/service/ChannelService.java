package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Channel;
import repo.ChannelRepository;

@Service
public class ChannelService {
	 @Autowired
	 private ChannelRepository repo;
    public List<Channel> findAll() { return repo.findAll(); }
    public Channel save(Channel c) { return repo.save(c); }
    public void delete(Long id) { repo.deleteById(id); }
    public Channel findById(Long id) {
        return repo.findById(id).orElse(null);
    }

}