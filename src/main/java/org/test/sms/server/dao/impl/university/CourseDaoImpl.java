package org.test.sms.server.dao.impl.university;

import org.springframework.stereotype.Repository;
import org.test.sms.common.entity.university.Course;
import org.test.sms.common.entity.university.Faculty;
import org.test.sms.common.filter.AbstractFilter;
import org.test.sms.common.filter.university.CourseFilter;
import org.test.sms.server.dao.impl.AbstractDaoImpl;
import org.test.sms.server.dao.interfaces.university.CourseDao;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CourseDaoImpl extends AbstractDaoImpl<Course> implements CourseDao {

    @Override
    public Optional<Course> get(long id) {
        Optional<Course> result = super.get(id);

        result.ifPresent(e -> e.getModules().size());

        return result;
    }

    @Override
    public List<Course> getList(AbstractFilter filter) {
        StringBuilder queryBuilder = new StringBuilder("FROM Course");
        Map<String, Object> params = new HashMap<>();

        if (Objects.nonNull(filter)) {
            queryBuilder.append(" WHERE 1 = 1");
            addFilter(queryBuilder, params, filter);
        }

        queryBuilder.append(" ORDER BY name");

        TypedQuery<Course> query = em.createQuery(queryBuilder.toString(), Course.class);
        params.keySet().forEach(e -> query.setParameter(e, params.get(e)));

        return query.getResultList();
    }

    @Override
    protected void addFilter(StringBuilder queryBuilder, Map<String, Object> params, AbstractFilter abstractFilter) {
        CourseFilter filter = (CourseFilter) abstractFilter;

        Faculty faculty = filter.getFaculty();
        if (Objects.nonNull(faculty)) {
            queryBuilder.append(" AND faculty = :faculty");
            params.put("faculty", faculty);
        }
    }
}