package authController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import domain.Channel;
import service.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@RestController
@RequestMapping("/api/channels")
@CrossOrigin
public class ChannelController {
	@Autowired
	private ChannelService service;

	@GetMapping
	public List<Channel> getAll() {
		return service.findAll();
	}

	@PostMapping
	public Channel create(@RequestBody Channel c) {
		return service.save(c);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class ResourceNotFoundException extends RuntimeException {
	    public ResourceNotFoundException(String msg) {
	        super(msg);
	    }
	}

	@PutMapping("/{id}")
	public Channel update(@PathVariable Long id, @RequestBody Channel updatedChannel) {
	    Channel existingChannel = service.findById(id);
	    if (existingChannel == null) {
	        throw new ResourceNotFoundException("Channel not found with id: " + id);
	    }
	    existingChannel.setName(updatedChannel.getName());
	    existingChannel.setType(updatedChannel.getType());
	    existingChannel.setMetadata(updatedChannel.getMetadata());

	    return service.save(existingChannel);
	}
}
