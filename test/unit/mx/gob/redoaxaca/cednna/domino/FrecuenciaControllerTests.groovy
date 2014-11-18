package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(FrecuenciaController)
@Mock(Frecuencia)
class FrecuenciaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/frecuencia/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.frecuenciaInstanceList.size() == 0
        assert model.frecuenciaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.frecuenciaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.frecuenciaInstance != null
        assert view == '/frecuencia/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/frecuencia/show/1'
        assert controller.flash.message != null
        assert Frecuencia.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/frecuencia/list'

        populateValidParams(params)
        def frecuencia = new Frecuencia(params)

        assert frecuencia.save() != null

        params.id = frecuencia.id

        def model = controller.show()

        assert model.frecuenciaInstance == frecuencia
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/frecuencia/list'

        populateValidParams(params)
        def frecuencia = new Frecuencia(params)

        assert frecuencia.save() != null

        params.id = frecuencia.id

        def model = controller.edit()

        assert model.frecuenciaInstance == frecuencia
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/frecuencia/list'

        response.reset()

        populateValidParams(params)
        def frecuencia = new Frecuencia(params)

        assert frecuencia.save() != null

        // test invalid parameters in update
        params.id = frecuencia.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/frecuencia/edit"
        assert model.frecuenciaInstance != null

        frecuencia.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/frecuencia/show/$frecuencia.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        frecuencia.clearErrors()

        populateValidParams(params)
        params.id = frecuencia.id
        params.version = -1
        controller.update()

        assert view == "/frecuencia/edit"
        assert model.frecuenciaInstance != null
        assert model.frecuenciaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/frecuencia/list'

        response.reset()

        populateValidParams(params)
        def frecuencia = new Frecuencia(params)

        assert frecuencia.save() != null
        assert Frecuencia.count() == 1

        params.id = frecuencia.id

        controller.delete()

        assert Frecuencia.count() == 0
        assert Frecuencia.get(frecuencia.id) == null
        assert response.redirectedUrl == '/frecuencia/list'
    }
}
