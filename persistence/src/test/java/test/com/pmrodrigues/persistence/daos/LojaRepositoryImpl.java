package test.com.pmrodrigues.persistence.daos;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.persistence.daos.impl.AbstractRepository;
import org.springframework.stereotype.Repository;
import test.com.pmrodrigues.persistence.models.Loja;

/**
 * Created by Marceloo on 12/11/2014.
 */
@Repository("ShoppingRepository")
public class LojaRepositoryImpl extends AbstractRepository<Loja> implements LojaRepository {

    @Override
    public ResultList<Loja> search(Loja loja) {
        return null;
    }
}
