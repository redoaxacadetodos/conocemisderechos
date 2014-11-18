package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(DependenciaController)
@Mock(Dependencia)
class DependenciaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dependencia/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dependenciaInstanceList.size() == 0
        assert model.dependenciaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dependenciaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dependenciaInstance != null
        assert view == '/dependencia/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dependencia/show/1'
        assert controller.flash.message != null
        assert Dependencia.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dependencia/list'

        populateValidParams(params)
        def dependencia = new Dependencia(params)

        assert dependencia.save() != null

        params.id = dependencia.id

        def model = controller.show()

        assert model.dependenciaInstance == dependencia
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dependencia/list'

        populateValidParams(params)
        def dependencia = new Dependencia(params)

        assert dependencia.save() != null

        params.id = dependencia.id

        def model = controller.edit()

        assert model.dependenciaInstance == dependencia
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dependencia/list'

        response.reset()

        populateValidParams(params)
        def dependencia = new Dependencia(params)

        assert dependencia.save() != null

        // test invalid parameters in update
        params.id = dependencia.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dependencia/edit"
        assert model.dependenciaInstance != null

        dependencia.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dependencia/show/$dependencia.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dependencia.clearErrors()

        populateValidParams(params)
        params.id = dependencia.id
        params.version = -1
        controller.update()

        assert view == "/dependencia/edit"
        assert model.dependenciaInstance != null
        assert model.dependenciaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dependencia/list'

        response.reset()

        populateValidParams(params)
        def dependencia = new Dependencia(params)

        assert dependencia.save() != null
        assert Dependencia.count() == 1

        params.id = dependencia.id

        controller.delete()

        assert Dependencia.count() == 0
        assert Dependencia.get(dependencia.id) == null
        assert response.redirectedUrl == '/dependencia/list'
    }
}
