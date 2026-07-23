CREATE TABLE produto (
                              id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                              categoria        VARCHAR(50) NOT NULL,
                              quantidade   INT NOT NULL,
                              banda_id BIGINT NOT NULL,
                              FOREIGN KEY (banda_id) REFERENCES banda(id)
                            
    
    
); 